package betahouse.service.club;

import betahouse.mapper.ClubActivityFormMapper;
import betahouse.mapper.ClubActivityStatusMapper;
import betahouse.mapper.UserInfoMapper;
import betahouse.model.ClubActivityForm;
import betahouse.model.ClubActivityStatus;
import betahouse.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static betahouse.core.constant.FormConstant.*;

/**
 * Created by x1654 on 2017/7/5.
 */
@Service
public class ClubActivityStatusServiceImpl implements ClubActivityStatusService {

    @Autowired
    private ClubActivityStatusMapper clubActivityStatusMapper;

    @Autowired
    private ClubActivityFormMapper clubActivityFormMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<ClubActivityStatus> listAll() {
        return clubActivityStatusMapper.selectAll();
    }

    @Override
    public ClubActivityStatus getStatusById(int id) {
        return clubActivityStatusMapper.selectByPrimaryKey(id);
    }

    @Override
    public ClubActivityStatus getStatusByFormId(int formId) {
        return clubActivityStatusMapper.selectByFormId(formId);
    }

    @Override
    public int commitFormStatus(ClubActivityForm form, UserInfo userInfo) {
        ClubActivityStatus clubActivityStatusDTO = new ClubActivityStatus();
        clubActivityStatusDTO.setFormId(form.getId());
        clubActivityStatusDTO.setFormType(CLUB_ACTIVITY_TYPE);
        clubActivityStatusDTO.setFormUserId(userInfo.getId());
        clubActivityStatusDTO.setStatus(0);
        clubActivityStatusDTO.setApproveLv(2);
        return clubActivityStatusMapper.insert(clubActivityStatusDTO);
    }

    @Override
    public Map listStatusByFormUserId(int formUserId) {
        List<ClubActivityStatus> listDTO = clubActivityStatusMapper.selectByFormUserId(formUserId);
        Map<Integer, String[]> mapDTO = new HashMap<Integer, String[]>();
        for(ClubActivityStatus c: listDTO){
            ClubActivityForm clubActivityFormDTO = clubActivityFormMapper.selectByPrimaryKey(c.getFormId());
            int idDTO = clubActivityFormDTO.getId();
            String activityNameDTO = clubActivityFormDTO.getActivityName();
            String[] arrDTO = new String[]{activityNameDTO, CLUB_ACTIVITY_STATUS_1[c.getStatus()],
                    c.getApproveLv()!=99?CLUB_ACTIVITY_STATUS_2[c.getApproveLv()-1]:CLUB_ACTIVITY_NOT_PASS};
            mapDTO.put(idDTO, arrDTO);
        }
        return mapDTO;
    }

    @Override
    public Map listStatusByTypeAndLv(int type, int lv) {
        List<ClubActivityStatus> listDTO = clubActivityStatusMapper.selectByTypeAndLv(type, lv);
        Map<Integer, String[]> mapDTO = new HashMap<Integer, String[]>();
        for(ClubActivityStatus c: listDTO){
            ClubActivityForm clubActivityFormDTO = clubActivityFormMapper.selectByPrimaryKey(c.getFormId());
            int idDTO = clubActivityFormDTO.getId();
            String activityNameDTO = clubActivityFormDTO.getActivityName();
            String[] arrDTO = new String[]{activityNameDTO, CLUB_ACTIVITY_STATUS_1[c.getStatus()],
                    c.getApproveLv()!=99?CLUB_ACTIVITY_STATUS_2[c.getApproveLv()-1]:CLUB_ACTIVITY_NOT_PASS};
            mapDTO.put(idDTO, arrDTO);
        }
        return mapDTO;
    }

    @Override
    public Map listStatusOverTypeAndLv(int type, int lv) {
        List<ClubActivityStatus> listDTO = clubActivityStatusMapper.selectOverTypeAndLv(type, lv);
        Map<Integer, String[]> mapDTO = new HashMap<Integer, String[]>();
        for(ClubActivityStatus c: listDTO){
            ClubActivityForm clubActivityFormDTO = clubActivityFormMapper.selectByPrimaryKey(c.getFormId());
            int idDTO = clubActivityFormDTO.getId();
            String activityNameDTO = clubActivityFormDTO.getActivityName();
            String[] arrDTO = new String[]{activityNameDTO, CLUB_ACTIVITY_STATUS_1[c.getStatus()],
                    c.getApproveLv()!=99?CLUB_ACTIVITY_STATUS_2[c.getApproveLv()-1]:CLUB_ACTIVITY_NOT_PASS};
            mapDTO.put(idDTO, arrDTO);
        }
        return mapDTO;
    }

    @Override
    public int updateStatusByFormId(int formId, int status, int approveLv, String approveDate) {
        ClubActivityStatus clubActivityStatusDTO = new ClubActivityStatus();
        clubActivityStatusDTO.setFormId(formId);
        clubActivityStatusDTO.setStatus(status);
        clubActivityStatusDTO.setApproveLv(approveLv);
        clubActivityStatusDTO.setApproveDate(approveDate);
        return clubActivityStatusMapper.updateByFormId(clubActivityStatusDTO);
    }

    @Override
    public int deleteStatusByFormId(int formId) {
        return clubActivityStatusMapper.deleteByFormId(formId);
    }
}
