package betahouse.service.user;

import betahouse.model.UserInfo;

import java.util.List;

/**
 * Created by x1654 on 2017/7/3.
 */
public interface UserInfoService {

    int insert(int id, String realName, String schoolId, String email, String tel);

    UserInfo getUserInfoById(int id);

    List<UserInfo> listAllUserInfo();

}
