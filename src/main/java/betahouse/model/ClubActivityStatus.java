package betahouse.model;

public class ClubActivityStatus {
    private Integer id;

    private Integer formId;

    private String formUserId;

    private String status;

    private String approveDate;

    private Integer approveLv;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getFormUserId() {
        return formUserId;
    }

    public void setFormUserId(String formUserId) {
        this.formUserId = formUserId == null ? null : formUserId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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