package betahouse.model;

public class FormManager {
    private Integer id;

    private Integer formType;

    private Integer approver;

    private Integer approverLv;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

    public Integer getApprover() {
        return approver;
    }

    public void setApprover(Integer approver) {
        this.approver = approver;
    }

    public Integer getApproverLv() {
        return approverLv;
    }

    public void setApproverLv(Integer approverLv) {
        this.approverLv = approverLv;
    }
}