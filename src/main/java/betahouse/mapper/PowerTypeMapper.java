package betahouse.mapper;

import betahouse.model.PowerType;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface PowerTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PowerType record);

    PowerType selectByPrimaryKey(Integer id);

    List<PowerType> selectAll();

    int updateByPrimaryKey(PowerType record);
}