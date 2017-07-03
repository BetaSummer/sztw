package betahouse.controller;

import betahouse.mapper.AdminMapper;
import betahouse.mapper.UserInfoMapper;
import betahouse.mapper.UserMapper;
import betahouse.model.Admin;
import betahouse.model.User;
import betahouse.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by x1654 on 2017/7/3.
 */
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserMapper userMapper;

    public Admin checkLogin(String username, String password){
        String usernameDTO = null;
        String passwordDTO = null;
        Admin adminDTO = adminMapper.selectByPrimaryKey(username);
        usernameDTO = adminDTO.getUsername();
        passwordDTO = adminDTO.getPassword();
        if(!username.equals(usernameDTO)){
            return null;
        }else if(!password.equals(passwordDTO)){
            return null;
        }else {
            return 
        }

    }
}
