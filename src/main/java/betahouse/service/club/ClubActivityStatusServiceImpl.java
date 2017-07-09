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
        Map<Integer, String[]> mapDTO = new HashMap<Integer, String[]>();
        for(ClubActivityStatus c: listDTO){
            ClubActivityForm clubActivityFormDTO = clubActivityFormMapper.selectByPrimaryKey(c.getFormId());
            int statusDTO = c.getStatus();
            String statusDTO2 = "";
            if(statusDTO==0){
                statusDTO2 = "未审核";
            }else if(statusDTO==1){
                statusDTO2 = "审核中";
            }else if(statusDTO == 2){
                statusDTO2 = "审核通过";
            }
            int idDTO = clubActivityFormDTO.getId();
            String activityNameDTO = clubActivityFormDTO.getActivityName();
            String[] arrDTO = new String[]{activityNameDTO, statusDTO2};
            mapDTO.put(idDTO, arrDTO);
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
        Map<Integer, Map> mapDTO = new HashMap<Integer, Map>();
        for(ClubActivityStatus c: listDTO){
            ClubActivityForm clubActivityFormDTO = clubActivityFormMapper.selectByPrimaryKey(c.getFormId());
            int statusDTO = c.getStatus();
            int idDTO = clubActivityFormDTO.getId();
            String activityNameDTO = clubActivityFormDTO.getActivityName();
            Map<String, Integer> mapDTO2 = new HashMap<String, Integer>();
            mapDTO2.put(activityNameDTO, statusDTO);
            mapDTO.put(idDTO, mapDTO2);
        }
        return mapDTO;
    }
}
