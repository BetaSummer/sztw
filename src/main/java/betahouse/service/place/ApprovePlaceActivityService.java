package betahouse.service.place;

import betahouse.model.ApprovePlaceActivity;

import java.util.List;

public interface ApprovePlaceActivityService {

    int saveApprove(int approveUserId, int isApprove, int formId, String comment);

    List<ApprovePlaceActivity> listApproveByFormId(int formId);

}
