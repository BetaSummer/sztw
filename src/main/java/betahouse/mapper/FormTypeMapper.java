package betahouse.mapper;

import betahouse.model.FormType;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FormTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FormType record);

    FormType selectByPrimaryKey(Integer id);

    List<FormType> selectAll();

    int updateByPrimaryKey(FormType record);

    FormType selectByFormType(Integer formType);

    FormType selectByFormName(String formName);
}