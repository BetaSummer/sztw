package betahouse.mapper;

import betahouse.model.StatusPlaceActivity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface StatusPlaceActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StatusPlaceActivity record);

    StatusPlaceActivity selectByPrimaryKey(Integer id);

    List<StatusPlaceActivity> selectAll();

    int updateByPrimaryKey(StatusPlaceActivity record);

    int updateStatusByFormId(StatusPlaceActivity record);

    StatusPlaceActivity selectByFormId(int formId);

    List<StatusPlaceActivity> selectByLv(Integer lv);

    List<StatusPlaceActivity> selectByLvAndResourcesStatus(@Param("lv") Integer lv, @Param("resourcesStatus") Integer status);

    List<StatusPlaceActivity> selectByLvAndPublicStatus(@Param("lv") Integer lv, @Param("publicStatus") Integer status);

    List<StatusPlaceActivity> selectOverLv(Integer lv);
}