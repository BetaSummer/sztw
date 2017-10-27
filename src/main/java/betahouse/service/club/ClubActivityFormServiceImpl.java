package betahouse.service.club;

import betahouse.core.mail.Mail;
import betahouse.core.mail.MailCore;
import betahouse.mapper.ClubActivityFormMapper;
import betahouse.mapper.ClubMapper;
import betahouse.model.Club;
import betahouse.model.ClubActivityForm;
import betahouse.model.FormManager;
import betahouse.model.UserInfo;
import betahouse.service.form.FormManagerService;
import betahouse.service.power.PowerService;
import betahouse.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by x1654 on 2017/7/4.
 */
@Service
public class ClubActivityFormServiceImpl implements ClubActivityFormService {

    @Autowired
    private ClubActivityFormMapper clubActivityFormMapper;

    @Autowired
    private ClubMapper clubMapper;

    @Autowired
    private PowerService powerService;

    @Autowired
    private MailCore mailCore;

    @Autowired
    private FormManagerService formManagerService;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public int commitForm(String club, String activityName, String activityPlace,
                          String activityTime, String activityPeople, String isApplyFine, String activityInfo,
                          String applySelfMoney, String applyReserveMoney, int fileId, UserInfo userInfo) {
        int isApplyFineDTO = Integer.parseInt(isApplyFine);
        float applySelfMoneyDTO = Float.parseFloat(applySelfMoney);
        float applyReserveMoneyDTO = Float.parseFloat(applyReserveMoney);
        ClubActivityForm clubActivityFormDTO = new ClubActivityForm();
        clubActivityFormDTO.setClub(club);
        clubActivityFormDTO.setChiefName(userInfo.getRealName());
        clubActivityFormDTO.setActivityName(activityName);
        clubActivityFormDTO.setActivityPlace(activityPlace);
        clubActivityFormDTO.setActivityTime(activityTime);
        clubActivityFormDTO.setActivityPeople(activityPeople);
        clubActivityFormDTO.setIsApplyFine(isApplyFineDTO);
        clubActivityFormDTO.setActivityInfo(activityInfo);
        clubActivityFormDTO.setApplySelfMoney(applySelfMoneyDTO);
        clubActivityFormDTO.setApplyReserveMoney(applyReserveMoneyDTO);
        clubActivityFormDTO.setClubId(clubMapper.selectByUserId(userInfo.getId()).getId());
        if(fileId!=0){
            clubActivityFormDTO.setFileId(fileId);
        }
        clubActivityFormDTO.setChiefId(userInfo.getSchoolId());
        clubActivityFormDTO.setChiefTel(userInfo.getTel());
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        clubActivityFormDTO.setApplyDate(sdfDTO.format(dateDTO));
        clubActivityFormMapper.insert(clubActivityFormDTO);

        Mail mailDTO = new Mail();
        mailDTO.setSubject("社团活动审批表-"+club+"-"+activityName);
        mailDTO.setPersonal("数字团委");
        mailDTO.setContext(
                club+"计划于"+clubActivityFormDTO.getActivityTime()+"在"+activityPlace+"举行"+activityName+"活动，希望尽快得到你们的批准。<br>"+
                "<a href=\"http://120.25.240.194:8080/applyClubForm/getFormById?id="+clubActivityFormDTO.getId()+"\">点击查看</a>");
        List<FormManager> listDTO = formManagerService.listFormManagerByFormTypeAndLv(1, 2);
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

        return clubActivityFormDTO.getId();
    }

    @Override
    public ClubActivityForm getFormById(int id) {
        return clubActivityFormMapper.selectByPrimaryKey(id);
    }

    @Override
    public ClubActivityForm getLastForm() {
        return clubActivityFormMapper.selectAll().get(0);
    }

    @Override
    public int deleteFormById(int userId, int id) {
        if(powerService.checkPower(userId, 9)||powerService.checkPower(userId, 3)){
            return clubActivityFormMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public List<ClubActivityForm> listFormByClubName(String clubName) {
        return clubActivityFormMapper.selectByClubName(clubName);
    }

    @Override
    public int updateFormById(int id, Club club) {
        ClubActivityForm clubActivityFormDTO = new ClubActivityForm();
        clubActivityFormDTO.setId(id);
        clubActivityFormDTO.setSelfMoney(club.getSelfMoney());
        clubActivityFormDTO.setReserveMoney(club.getReserveMoney());
        return clubActivityFormMapper.updateByPrimaryKey(clubActivityFormDTO);
    }

    @Override
    public int deleteFormByClubId(int userId, int clubId) {
        if(powerService.checkPower(userId, 9)||powerService.checkPower(userId, 3)){
            return clubActivityFormMapper.deleteByClubId(clubId);
        }
        return 0;
    }

    @Override
    public List<ClubActivityForm> listFormByClubId(int clubId) {
        return clubActivityFormMapper.selectByClubId(clubId);
    }
}
