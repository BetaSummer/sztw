package betahouse.service.club;

import betahouse.mapper.*;
import betahouse.model.*;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public int saveApprove(UserInfo userInfo, int isApprove, int formId, String comment, int applySelfMoney, int applyReserveMoney) {
        int clubIdDTO = clubActivityFormService.getFormById(formId).getClubId();
        if(isApprove==1){
            if(clubActivityStatusService.getStatusByFormId(formId).getApproveLv()==4){
                Club clubDTO = clubMapper.selectByPrimaryKey(clubIdDTO);
                clubActivityFormService.updateFormById(formId, clubDTO);
                clubService.updateMoneyById(clubIdDTO, applySelfMoney, applyReserveMoney);
                clubActivityStatusService.updateStatusByFormId(formId, 1);
            }
            clubActivityStatusService.updateLvByFormId(formId);
        }else if(isApprove==0){
            clubActivityStatusService.updateStatusByFormId(formId, 2);
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
    public List<ClubActivityApprove> listApproveByLvAndFormId(int lv, int formId) {
        return clubActivityApproveMapper.selectByLvAndFormId(lv, formId);
    }

    @Override
    public List<ClubActivityApprove> listApproveByFormId(int formId) {
        return clubActivityApproveMapper.selectByFormId(formId);
    }
}
