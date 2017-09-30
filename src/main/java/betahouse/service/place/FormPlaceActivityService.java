package betahouse.service.place;

import betahouse.model.FormPlaceActivity;
import betahouse.model.VO.PlaceActivityTableVO;

public interface FormPlaceActivityService {

    int commitForm(int formUserId, String userTel,String activityName, String activityPlace, String activityDate, String list, String content,
                   String budget, String method, int resourcesStatus);

    int commitForm(int formUserId, String userTel, String activityName, String activityPlace, String activityDate, String list, String content,
                   String budget, String method, int resourcesStatus, String water, String electric, String start, String end);

    PlaceActivityTableVO getPlaceActivityTableVOById(int id);

    FormPlaceActivity getFormById(int id);

}
