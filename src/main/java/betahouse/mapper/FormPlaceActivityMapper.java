package betahouse.mapper;

import betahouse.model.FormPlaceActivity;
import java.util.List;

public interface FormPlaceActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FormPlaceActivity record);

    FormPlaceActivity selectByPrimaryKey(Integer id);

    List<FormPlaceActivity> selectAll();

    int updateByPrimaryKey(FormPlaceActivity record);
}