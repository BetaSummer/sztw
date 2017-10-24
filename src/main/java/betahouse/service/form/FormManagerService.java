package betahouse.service.form;

import betahouse.model.FormManager;

import java.util.List;

/**
 * Created by x1654 on 2017/7/10.
 */
public interface FormManagerService {

    FormManager getFormManagerByApprover(int approverId);

    int insertFormManager(int approver, String approverForm);

    int updateFormManagerByApprover(int approver, String powerList, String lvList);

    List<FormManager> listFormManagerByFormTypeAndLv(int formType, int lv);

    int updateFormManagerByUserId(int userId, int formType, int lv);

}
