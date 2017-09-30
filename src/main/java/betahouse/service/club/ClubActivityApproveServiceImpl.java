package betahouse.service.club;

import betahouse.mapper.*;
import betahouse.model.*;
import betahouse.service.financial.ClubFinancialFlowService;
import betahouse.service.form.FormManagerService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    private FormManagerService formManagerService;

    @Autowired
    private ClubFinancialFlowService clubFinancialFlowService;


    @Override
    public int saveApprove(UserInfo userInfo, int isApprove, int formId, String comment, float applySelfMoney, float applyReserveMoney) {
        int clubIdDTO = clubActivityFormService.getFormById(formId).getClubId();
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
            }
            clubActivityStatusService.updateStatusByFormId(formId, 0, approveLvDTO+1, null);
        }else if(isApprove==0){
            clubActivityStatusService.updateStatusByFormId(formId, 2, 99, null);
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
