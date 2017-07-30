package betahouse.mapper;

import betahouse.model.StatusPlaceActivity;
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
}