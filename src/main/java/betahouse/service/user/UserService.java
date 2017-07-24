package betahouse.service.user;

import betahouse.model.User;

import java.util.List;

/**
 * Created by x1654 on 2017/7/3.
 */
public interface UserService {

    User checkLogin(String username, String password);

    User getUserById(int id);

    User getUserByUsername(String username);

    List<User> listAllUser();

    int register(String username, String password);

    int updateUserById(int id, String username, String password);

}
