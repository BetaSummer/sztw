package betahouse.model;

public class StatusPlaceActivity {
    private Integer id;

    private Integer formType;

    private Integer formId;

    private Integer formUserId;

    private Integer status;

    private String approveDate;

    private Integer approveLv;

    private Integer resourcesStatus;

    private Integer publicStatus;

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

    public Integer getResourcesStatus() {
        return resourcesStatus;
    }

    public void setResourcesStatus(Integer resourcesStatus) {
        this.resourcesStatus = resourcesStatus;
    }

    public Integer getPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(Integer publicStatus) {
        this.publicStatus = publicStatus;
    }
}