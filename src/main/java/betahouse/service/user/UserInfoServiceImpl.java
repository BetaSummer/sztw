package betahouse.service.user;

import betahouse.mapper.UserInfoMapper;
import betahouse.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by x1654 on 2017/7/3.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int insert(int id, String realName, String schoolId, String email, String tel) {
        UserInfo userInfoDTO = new UserInfo();
        userInfoDTO.setId(id);
        userInfoDTO.setRealName(realName);
        userInfoDTO.setSchoolId(schoolId);
        userInfoDTO.seteMail(email);
        userInfoDTO.setTel(tel);
        return userInfoMapper.insert(userInfoDTO);
    }

    @Override
    public UserInfo getUserInfoById(int id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserInfo> listAllUserInfo() {
        return userInfoMapper.selectAll();
    }

    @Override
    public int updateUserInfoById(int id, String realName, String schoolId, String eMail, String tel) {
        UserInfo userInfoDTO = new UserInfo();
        userInfoDTO.setId(id);
        userInfoDTO.setRealName(realName);
        userInfoDTO.setSchoolId(schoolId);
        userInfoDTO.seteMail(eMail);
        userInfoDTO.setTel(tel);
        return userInfoMapper.updateByPrimaryKey(userInfoDTO);
    }
}
