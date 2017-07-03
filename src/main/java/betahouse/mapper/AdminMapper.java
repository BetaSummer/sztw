package betahouse.mapper;

import betahouse.model.Admin;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer id);

    Admin selectByPrimaryKey(String username);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);
}