package betahouse.mapper;

import betahouse.model.ApprovePlaceActivity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ApprovePlaceActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApprovePlaceActivity record);

    ApprovePlaceActivity selectByPrimaryKey(Integer id);

    List<ApprovePlaceActivity> selectAll();

    int updateByPrimaryKey(ApprovePlaceActivity record);

    List<ApprovePlaceActivity> selectByFormId(int formId);

    ApprovePlaceActivity selectByLvAndFormId(@Param("lv") Integer lv, @Param("formId") Integer formId);
}