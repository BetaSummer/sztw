package betahouse.mapper;

import betahouse.model.FormPlaceActivity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FormPlaceActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FormPlaceActivity record);

    FormPlaceActivity selectByPrimaryKey(Integer id);

    List<FormPlaceActivity> selectAll();

    int updateByPrimaryKey(FormPlaceActivity record);
}