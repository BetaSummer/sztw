package betahouse.mapper;

import betahouse.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}