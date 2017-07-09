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

import static betahouse.core.constant.FormConstant.CLUB_ACTIVITY_TYPE;

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
    public int saveStatus(ClubActivityForm form, UserInfo userInfo) {
        ClubActivityStatus clubActivityStatusDTO = new ClubActivityStatus();
        clubActivityStatusDTO.setFormId(form.getId());
        clubActivityStatusDTO.setFormType(CLUB_ACTIVITY_TYPE);
        clubActivityStatusDTO.setFormUserId(userInfo.getId());
        clubActivityStatusDTO.setStatus(0);
        clubActivityStatusDTO.setApproveLv(1);
        return clubActivityStatusMapper.insert(clubActivityStatusDTO);
    }

    @Override
    public Map listStatusByFormUserId(int formUserId) {
        List<ClubActivityStatus> listDTO = clubActivityStatusMapper.selectByFormUserId(formUserId);
        Map<String, Integer> mapDTO = new HashMap<String, Integer>();
        for(ClubActivityStatus c: listDTO){
            int status = c.getStatus();
            String activityName = clubActivityFormMapper.selectByPrimaryKey(c.getFormId()).getActivityName();
            mapDTO.put(activityName, status);
        }
        return mapDTO;
    }

    @Override
    public int updateLvByFormId(int lv, int formId) {
        if(lv>4){
            return 1;
        }
        ClubActivityStatus clubActivityStatusDTO = new ClubActivityStatus();
        clubActivityStatusDTO.setFormId(formId);
        clubActivityStatusDTO.setApproveLv(lv+1);
        clubActivityStatusMapper.updateByFormId(clubActivityStatusDTO);
        return 0;
    }

    @Override
    // TODO: 2017/7/7 type and lv
    public Map listAllByLv(int lv) {
        List<ClubActivityStatus> listDTO = clubActivityStatusMapper.selectByLv(lv);
        Map<String, Integer> mapDTO = new HashMap<String, Integer>();
        for(ClubActivityStatus c: listDTO){
            int status = c.getStatus();
            String activityName = clubActivityFormMapper.selectByPrimaryKey(c.getFormId()).getActivityName();
            mapDTO.put(activityName, status);
        }
        return mapDTO;
    }
}
