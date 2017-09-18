package betahouse.service.form;

import betahouse.model.FormManager;

/**
 * Created by x1654 on 2017/7/10.
 */
public interface FormManagerService {

    FormManager getFormManagerByApprover(int approverId);

    int insertFormManager(int approver, String approverForm);

    int updateFormManagerByApprover(int approver, String powerList, String lvList);

}
