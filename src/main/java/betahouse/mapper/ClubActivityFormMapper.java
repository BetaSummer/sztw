package betahouse.mapper;

import betahouse.model.ClubActivityForm;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ClubActivityFormMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClubActivityForm record);

    ClubActivityForm selectByPrimaryKey(Integer id);

    List<ClubActivityForm> selectAll();

    int updateByPrimaryKey(ClubActivityForm record);

    int selectLastInsertId();

}