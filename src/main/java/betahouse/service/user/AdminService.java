package betahouse.service.user;

import betahouse.mapper.AdminMapper;
import betahouse.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by x1654 on 2017/7/3.
 */
public interface AdminService {

    Admin checkLogin(String username, String password);

    List<Admin> listAllUser();

    Admin getUserById(int id);

    Admin getUserByUsername(String username);

}
