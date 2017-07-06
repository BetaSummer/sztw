package betahouse.service.user;

import betahouse.mapper.AdminMapper;
import betahouse.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by x1654 on 2017/7/3.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin checkLogin(String username, String password){
        Admin adminDTO = adminMapper.selectByUsername(username);
        if(adminDTO == null){
            return null;
        }
        String passwordDTO = adminDTO.getPassword();
        if(!password.equals(passwordDTO)){
            return null;
        }
        return adminDTO;
    }

    @Override
    public List<Admin> listAllUser() {
        return adminMapper.selectAll();
    }

    @Override
    public Admin getUserById(int id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Admin getUserByUsername(String username) {
        return adminMapper.selectByUsername(username);
    }

}
