package betahouse.service.user;

import betahouse.model.UserInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by x1654 on 2017/7/3.
 */
@Component
public interface UserInfoService {

    int insert(int id, String realName, String schoolId, String email, String tel);

    UserInfo getUserInfoById(int id);

    List<UserInfo> listAllUserInfo();

    int updateUserInfoById(int id, String realName, String schoolId, String eMail, String tel);
}
