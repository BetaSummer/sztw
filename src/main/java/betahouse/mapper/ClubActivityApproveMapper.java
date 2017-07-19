package betahouse.mapper;

import betahouse.model.ClubActivityApprove;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ClubActivityApproveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClubActivityApprove record);

    ClubActivityApprove selectByPrimaryKey(Integer id);

    List<ClubActivityApprove> selectAll();

    int updateByPrimaryKey(ClubActivityApprove record);

    List<ClubActivityApprove> selectByLv(Integer lv);

    ClubActivityApprove selectByLvAndFormId(@Param("lv") Integer lv, @Param("formId") Integer formId);

    List<ClubActivityApprove> selectByFormId(Integer formId);
}