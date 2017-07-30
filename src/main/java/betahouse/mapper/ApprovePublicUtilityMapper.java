package betahouse.mapper;

import betahouse.model.ApprovePublicUtility;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ApprovePublicUtilityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApprovePublicUtility record);

    ApprovePublicUtility selectByPrimaryKey(Integer id);

    List<ApprovePublicUtility> selectAll();

    int updateByPrimaryKey(ApprovePublicUtility record);
}