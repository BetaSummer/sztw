package betahouse.mapper;

import betahouse.model.ClubActivityStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ClubActivityStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClubActivityStatus record);

    ClubActivityStatus selectByPrimaryKey(Integer id);

    List<ClubActivityStatus> selectAll();

    int updateByPrimaryKey(ClubActivityStatus record);

    ClubActivityStatus selectByFormId(Integer formId);

    int updateByFormId(ClubActivityStatus record);

    List<ClubActivityStatus> selectByTypeAndLv(@Param("formType") Integer formType, @Param("lv") Integer lv);

    List<ClubActivityStatus> selectOverTypeAndLv(@Param("formType") Integer formType, @Param("lv") Integer lv);

    List<ClubActivityStatus> selectByFormUserId(Integer formUserId);
}