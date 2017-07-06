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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // FIXME: 2017/7/5
//        clubActivityStatusDTO.setFormUserId(user.getId());
//        return clubActivityStatusMapper.insert();
        return 0;
    }

    @Override
    public Map listStatusByFormUserId(int formUserId) {
        Map m = new HashMap<Integer, String>();
        // TODO: 2017/7/6
        //m.put(1, "活动名称");
        return m;
    }

    @Override
    public int updateLvByFormId(int lv, int formId) {
        // FIXME: 2017/7/5
        if(lv>4){
            return 0;
        }
        ClubActivityStatus clubActivityStatusDTO = new ClubActivityStatus();
        clubActivityStatusDTO.setFormId(formId);
        clubActivityStatusDTO.setApproveLv(lv+1);
        clubActivityStatusMapper.updateByFormId(clubActivityStatusDTO);
        return 0;
    }

    @Override
    public Map listAllByLv(int lv) {
        // TODO: 2017/7/6 取出指定lv的formid 再通过id获取对应表单数据
        List<ClubActivityStatus> listDTO = clubActivityStatusMapper.listStatusByLv(lv);
        String[][] res = new String[listDTO.size()][7];

        return null;
    }
}
