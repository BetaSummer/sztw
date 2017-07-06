package betahouse.service.user;

import betahouse.mapper.UserInfoMapper;
import betahouse.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
