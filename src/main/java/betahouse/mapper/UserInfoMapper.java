package betahouse.mapper;

import betahouse.model.UserInfo;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);
}