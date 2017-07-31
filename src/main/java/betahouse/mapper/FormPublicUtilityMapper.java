package betahouse.mapper;

import betahouse.model.FormPublicUtility;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FormPublicUtilityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FormPublicUtility record);

    FormPublicUtility selectByPrimaryKey(Integer id);

    List<FormPublicUtility> selectAll();

    int updateByPrimaryKey(FormPublicUtility record);

    FormPublicUtility selectByFormId(int formId);
}