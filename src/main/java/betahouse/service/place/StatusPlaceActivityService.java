package betahouse.service.place;

import betahouse.model.StatusPlaceActivity;

import java.util.Map;

public interface StatusPlaceActivityService {

    int insert(int formId, int formUserId, int resourcesStatus, int publicStatus);

    int updateStatusByFormId(int formId, int status, int resourcesStatus, int publicStatus, int approveLv, String approveDate);

    StatusPlaceActivity getStatusByFormId(int formId);

    Map listStatusByUserId(int userId);

    Map listStatusOverUserId(int userId);

}
