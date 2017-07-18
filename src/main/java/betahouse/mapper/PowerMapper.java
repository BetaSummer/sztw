package betahouse.mapper;

import betahouse.model.Power;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface PowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Power record);

    Power selectByPrimaryKey(Integer id);

    List<Power> selectAll();

    int updateByPrimaryKey(Power record);

    Power selectByUserId(int userId);

    int updateByUserId(Power record);
}