package betahouse.mapper;

import betahouse.model.FormManager;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FormManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FormManager record);

    FormManager selectByPrimaryKey(Integer id);

    List<FormManager> selectAll();

    int updateByPrimaryKey(FormManager record);

    FormManager selectFormManagerByApprover(int approver);
}