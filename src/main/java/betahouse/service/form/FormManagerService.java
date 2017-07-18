package betahouse.service.form;

import betahouse.model.FormManager;

/**
 * Created by x1654 on 2017/7/10.
 */
public interface FormManagerService {

    FormManager getFormManagerByApprover(int approverId);

    int insertFormManager();

    int updateFormManagerByApprover(int approver, int formType, int lv);

}
