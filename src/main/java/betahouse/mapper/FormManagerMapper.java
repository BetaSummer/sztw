package betahouse.mapper;

import betahouse.model.FormManager;
import java.util.List;

public interface FormManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FormManager record);

    FormManager selectByPrimaryKey(Integer id);

    List<FormManager> selectAll();

    int updateByPrimaryKey(FormManager record);
}