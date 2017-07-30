package betahouse.service.place;

import betahouse.model.StatusPlaceActivity;

public interface StatusPlaceActivityService {

    int updateStatusByFormId(int formId, int status, int resourcesStatus, int publicStatus, int approveLv, String approveDate);

    StatusPlaceActivity getStatusByFormId(int formId);

}
