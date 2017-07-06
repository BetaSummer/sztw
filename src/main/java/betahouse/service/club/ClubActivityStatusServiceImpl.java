package betahouse.service.club;

import betahouse.mapper.ClubActivityFormMapper;
import betahouse.mapper.ClubActivityStatusMapper;
import betahouse.mapper.UserInfoMapper;
import betahouse.model.ClubActivityForm;
import betahouse.model.ClubActivityStatus;
import betahouse.model.User;
import betahouse.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int saveStatus(ClubActivityForm form, User user) {
        ClubActivityStatus clubActivityStatusDTO = new ClubActivityStatus();
        clubActivityStatusDTO.setFormId(form.getId());
        // FIXME: 2017/7/5 
//        clubActivityStatusDTO.setFormUserId(user.getId());
//        return clubActivityStatusMapper.insert();
        return 0;
    }

    @Override
    public String[][] listStatusByFormUserId(int formUserId) {
        return new String[0][];
        // FIXME: 2017/7/5 
    }

    @Override
    public int updateLvByFormId(User user, int formId) {
        String lv = userInfoMapper.selectByPrimaryKey(user.getId()).getLv();
        // FIXME: 2017/7/5
        if(Integer.parseInt(lv)>4){
            return 0;
        }
        ClubActivityStatus clubActivityStatusDTO = new ClubActivityStatus();
        clubActivityStatusDTO.setFormId(formId);
        clubActivityStatusDTO.setApproveLv(Integer.parseInt(lv)+1);
        clubActivityStatusMapper.updateByFormId(clubActivityStatusDTO);
        return 0;
    }

    @Override
    public String[][] listAllByLv(User user) {
        // FIXME: 2017/7/5
        String lv = userInfoMapper.selectByPrimaryKey(user.getId()).getLv();
        List<ClubActivityStatus> listDTO = clubActivityStatusMapper.listStatusByLv(Integer.parseInt(lv));
        String[][] res = new String[listDTO.size()][7];
        for(int i=0;i<listDTO.size();i++){
            res[i][0] = listDTO.get(i).getId().toString();
            res[i][1] = listDTO.get(i).getFormId().toString();
            res[i][2] = listDTO.get(i).getFormUserId();
            res[i][3] = listDTO.get(i).getStatus();
            res[i][4] = listDTO.get(i).getApproveDate();
            res[i][5] = listDTO.get(i).getApproveLv().toString();
            res[i][6] = clubActivityFormMapper.selectByPrimaryKey(listDTO.get(i).getId()).getActivityName();
        }
        return res;
    }
}
