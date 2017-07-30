package betahouse.service.place;

import betahouse.model.ApprovePublicUtility;

public interface ApprovePublicUtilityService {

    int insert(int formId, int isApprove, String defendComment, String electricComment, int approveUserId, String water, String electric, String start, String end);

}
