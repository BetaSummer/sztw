package betahouse.mapper;

import betahouse.model.FormType;
import java.util.List;

public interface FormTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FormType record);

    FormType selectByPrimaryKey(Integer id);

    List<FormType> selectAll();

    int updateByPrimaryKey(FormType record);
}