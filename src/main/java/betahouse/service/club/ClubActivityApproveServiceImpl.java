package betahouse.service.club;

import betahouse.core.mail.Mail;
import betahouse.core.mail.MailCore;
import betahouse.mapper.*;
import betahouse.model.*;
import betahouse.service.financial.ClubFinancialFlowService;
import betahouse.service.form.FormManagerService;
import betahouse.service.user.UserInfoService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static betahouse.core.constant.FormConstant.CLUB_ACTIVITY_STATUS_2;

/**
 * Created by x1654 on 2017/7/5.
 */
@Service
public class ClubActivityApproveServiceImpl implements ClubActivityApproveService{

    @Autowired
    private ClubMapper clubMapper;

    @Autowired
    private ClubActivityStatusMapper clubActivityStatusMapper;

    @Autowired
    private ClubActivityFormMapper clubActivityFormMapper;

    @Autowired
    private ClubActivityApproveMapper clubActivityApproveMapper;

    @Autowired
    private ClubActivityStatusService clubActivityStatusService;

    @Autowired
    private ClubActivityFormService clubActivityFormService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private ClubFinancialFlowService clubFinancialFlowService;

    @Autowired
    private MailCore mailCore;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private FormManagerService formManagerService;


    @Override
    public int saveApprove(UserInfo userInfo, int isApprove, int formId, String comment, float applySelfMoney, float applyReserveMoney) {
        ClubActivityForm clubActivityFormDTO = clubActivityFormService.getFormById(formId);
        int clubIdDTO = clubActivityFormDTO.getClubId();
        int approveLvDTO = clubActivityStatusService.getStatusByFormId(formId).getApproveLv();
        if(isApprove==1){
            if(approveLvDTO==4){
                Club clubDTO = clubMapper.selectByPrimaryKey(clubIdDTO);
                if(clubService.updateMoneyById(clubIdDTO, applySelfMoney, applyReserveMoney)==-1){
                    ;return -1;
                }
                clubActivityFormService.updateFormById(formId, clubDTO);
                Date dateDTO = new Date();
                SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                clubActivityStatusService.updateStatusByFormId(formId, 1, 0, sdfDTO.format(dateDTO));
                clubFinancialFlowService.insert(clubIdDTO, formId, applySelfMoney, applyReserveMoney, 0,
                        clubActivityFormService.getFormById(formId).getActivityName());

                Mail mailDTO = new Mail();
                mailDTO.setSubject("【通过】社团活动审批表-"+clubActivityFormDTO.getClub()+"-"+clubActivityFormDTO.getActivityName());
                mailDTO.setPersonal("数字团委");
                mailDTO.setContext(clubActivityFormDTO.getClub()+"社长，很高兴通知你，你申请的"+clubActivityFormDTO.getActivityName()+"已经审批通过，你可以尽快准备活动"+
                        "如需帮助请与社团联取得联系。<br><p style=\"text-align:center;\">感谢您使用由β-house提供的数字团学系统");
                try {
                    mailDTO.setAddresses(
                            userInfoService.getUserInfoBySchoolId(clubActivityFormDTO.getChiefId()).geteMail(),
                            clubActivityFormDTO.getChiefName());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                mailCore.sendMail(mailDTO);

            }else {
                Mail mailDTO = new Mail();
                mailDTO.setSubject("社团活动审批表-"+clubActivityFormDTO.getClub()+"-"+clubActivityFormDTO.getActivityName());
                mailDTO.setPersonal("数字团委");
                mailDTO.setContext(
                        clubActivityFormDTO.getClub()+"计划于"+clubActivityFormDTO.getActivityTime()+"在"+clubActivityFormDTO.getActivityPlace()+"举行"+clubActivityFormDTO.getActivityName()+"活动，已经"+CLUB_ACTIVITY_STATUS_2[approveLvDTO]+"望您尽快审批。<br>"+
                        "<a href=\"http://120.25.240.194:8080/applyClubForm/getFormById?id="+clubActivityFormDTO.getId()+"\">点击查看</a>");
                List<FormManager> listDTO = formManagerService.listFormManagerByFormTypeAndLv(1, approveLvDTO+1);
                List<String> addressListDTO = new ArrayList<>();
                List<String> receiverNamesDTO = new ArrayList<>();
                for(FormManager f: listDTO){
                    UserInfo userInfoDTO = userInfoService.getUserInfoById(f.getApprover());
                    addressListDTO.add(userInfoDTO.geteMail());
                    receiverNamesDTO.add(userInfoDTO.getRealName());
                }
                try {
                    mailDTO.setAddresses(addressListDTO, receiverNamesDTO);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                mailCore.sendMail(mailDTO);
            }
            clubActivityStatusService.updateStatusByFormId(formId, 0, approveLvDTO+1, null);



        }else if(isApprove==0){
            clubActivityStatusService.updateStatusByFormId(formId, 2, 99, null);

            Mail mailDTO = new Mail();
            mailDTO.setSubject("【未通过】社团活动审批表-"+clubActivityFormDTO.getClub()+"-"+clubActivityFormDTO.getActivityName());
            mailDTO.setPersonal("数字团委");
            mailDTO.setContext(clubActivityFormDTO.getClub()+"社长，很遗憾通知你，你申请的"+clubActivityFormDTO.getActivityName()+"审批未通过，"+
                    "如有疑问请与社团联取得联系。<br><p style=\"text-align:center;\">感谢您使用由β-house提供的数字团学系统");
            List<ClubActivityApprove>listDTO = listApproveByFormId(formId);
            List<String> addressListDTO = new ArrayList<>();
            List<String> receiverNamesDTO = new ArrayList<>();
            for(ClubActivityApprove c: listDTO){
                UserInfo userInfoDTO = userInfoService.getUserInfoById(c.getApproveUserId());
                addressListDTO.add(userInfoDTO.geteMail());
                receiverNamesDTO.add(userInfoDTO.getRealName());
            }
            addressListDTO.add(userInfoService.getUserInfoBySchoolId(clubActivityFormDTO.getChiefId()).geteMail());
            receiverNamesDTO.add(clubActivityFormDTO.getChiefName());
            try {
                mailDTO.setAddresses(addressListDTO, receiverNamesDTO);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            mailCore.sendMail(mailDTO);

        }
        String approveFormDTO = formManagerService.getFormManagerByApprover(userInfo.getId()).getApproverForm();
        int lvDTO = JSON.parseArray(approveFormDTO, Integer.class).get(0);
        ClubActivityApprove clubActivityApproveDTO = new ClubActivityApprove();
        clubActivityApproveDTO.setApproveUserId(userInfo.getId());
        clubActivityApproveDTO.setFormId(formId);
        clubActivityApproveDTO.setIsApprove(isApprove);
        clubActivityApproveDTO.setComment(comment);
        clubActivityApproveDTO.setLv(lvDTO);
        return clubActivityApproveMapper.insert(clubActivityApproveDTO);
    }

    @Override
    public List<ClubActivityApprove> listApproveByLv(int lv) {
        return clubActivityApproveMapper.selectByLv(lv);
    }

    @Override
    public ClubActivityApprove getApproveByLvAndFormId(int lv, int formId) {
        return clubActivityApproveMapper.selectByLvAndFormId(lv, formId);
    }

    @Override
    public List<ClubActivityApprove> listApproveByFormId(int formId) {
        return clubActivityApproveMapper.selectByFormId(formId);
    }

    @Override
    public int deleteApproveByFormId(int formId) {
        return clubActivityApproveMapper.deleteByFormId(formId);
    }
}
