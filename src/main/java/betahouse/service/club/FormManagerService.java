package betahouse.service.club;

import betahouse.model.FormManager;

/**
 * Created by x1654 on 2017/7/10.
 */
public interface FormManagerService {

    FormManager getFormManagerByApprover(int approverId);

    int insertFormManager();

    int updateFormManager(int approver);

}
