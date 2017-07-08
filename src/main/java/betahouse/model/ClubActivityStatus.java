package betahouse.model;

public class ClubActivityStatus {
    private Integer id;

    private Integer formType;

    private Integer formId;

    private Integer formUserId;

    private Integer status;

    private String approveDate;

    private Integer approveLv;

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

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Integer getFormUserId() {
        return formUserId;
    }

    public void setFormUserId(Integer formUserId) {
        this.formUserId = formUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate == null ? null : approveDate.trim();
    }

    public Integer getApproveLv() {
        return approveLv;
    }

    public void setApproveLv(Integer approveLv) {
        this.approveLv = approveLv;
    }
}