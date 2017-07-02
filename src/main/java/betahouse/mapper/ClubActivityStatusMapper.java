package betahouse.mapper;

import betahouse.model.ClubActivityStatus;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ClubActivityStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClubActivityStatus record);

    ClubActivityStatus selectByPrimaryKey(Integer id);

    List<ClubActivityStatus> selectAll();

    int updateByPrimaryKey(ClubActivityStatus record);
}