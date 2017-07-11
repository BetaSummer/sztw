package betahouse.model;

public class FormManager {
    private Integer id;

    private Integer approver;

    private String approverForm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApprover() {
        return approver;
    }

    public void setApprover(Integer approver) {
        this.approver = approver;
    }

    public String getApproverForm() {
        return approverForm;
    }

    public void setApproverForm(String approverForm) {
        this.approverForm = approverForm == null ? null : approverForm.trim();
    }
}