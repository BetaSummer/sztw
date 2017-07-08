package betahouse.service.club;

import betahouse.mapper.*;
import betahouse.model.*;
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
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ClubActivityApproveMapper clubActivityApproveMapper;


    @Override
    public int saveApprove(User user, int isApprove, int formId, String comment, int clubId, int applySelfMoney, int applyReserveMoney) {
        UserInfo userInfoDTO = userInfoMapper.selectByPrimaryKey(user.getId());
        // TODO: 2017/7/8 userInfo 中没有lv属性了
        //if(userInfoDTO.getLv()==4){
            Club club = clubMapper.selectByPrimaryKey(clubId);
            if(isApprove==1){
                ClubActivityStatus clubActivityStatusDTO = new ClubActivityStatus();
                clubActivityStatusDTO.setFormId(formId);
                clubActivityStatusDTO.setStatus(1);
                clubActivityStatusMapper.updateByFormId(clubActivityStatusDTO);
                ClubActivityForm clubActivityFormDTO = new ClubActivityForm();
                clubActivityFormDTO.setClubId(formId);
                clubActivityFormDTO.setSelfMoney(club.getSelfMoney());
                clubActivityFormDTO.setReserveMoney(club.getReserveMoney());
                clubActivityFormMapper.updateByPrimaryKey(clubActivityFormDTO);
                Club clubDTO = new Club();
                clubDTO.setId(clubId);
                club.setSelfMoney(club.getSelfMoney()-applySelfMoney);
                club.setReserveMoney(club.getReserveMoney()-applyReserveMoney);
                clubMapper.updateByPrimaryKey(clubDTO);
            }else if(isApprove==0){
                ClubActivityStatus clubActivityStatusDTO = new ClubActivityStatus();
                clubActivityStatusDTO.setFormId(formId);
                clubActivityStatusDTO.setStatus(2);
                clubActivityStatusMapper.updateByFormId(clubActivityStatusDTO);
            }
       // }
        ClubActivityApprove clubActivityApproveDTO = new ClubActivityApprove();
        clubActivityApproveDTO.setId(user.getId());
        clubActivityApproveDTO.setFormId(formId);
        clubActivityApproveDTO.setIsApprove(isApprove);
        clubActivityApproveDTO.setComment(comment);
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
