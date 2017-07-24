package betahouse.service.user;

import betahouse.core.Base.SimpleMD5;
import betahouse.mapper.UserMapper;
import betahouse.model.User;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by x1654 on 2017/7/3.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public User checkLogin(String username, String password) {
        password = SimpleMD5.MD5(password);
        User userDTO = userMapper.selectByUsername(username);
        if(userDTO == null){
            return null;
        }
        String passwordDTO = userDTO.getPassword();
        if(!passwordDTO.equals(password)){
            return null;
        }
        return userDTO;
    }

    @Override
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<User> listAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public int register(String username, String password) {
        User userDTO = new User();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        return userMapper.insert(userDTO);
    }

    @Override
    public int updateUserById(int id, String username, String password) {
        User userDTO = new User();
        userDTO.setId(id);
        userDTO.setUsername(username);
        userDTO.setPassword(SimpleMD5.MD5(password));
        return userMapper.updateByPrimaryKey(userDTO);
    }
}
