package betahouse.mapper;

import betahouse.model.PowerType;
import java.util.List;

public interface PowerTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PowerType record);

    PowerType selectByPrimaryKey(Integer id);

    List<PowerType> selectAll();

    int updateByPrimaryKey(PowerType record);
}