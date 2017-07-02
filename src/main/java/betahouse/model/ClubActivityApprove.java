package betahouse.model;

public class ClubActivityApprove {
    private Integer id;

    private Integer approveUserId;

    private Integer isApprove;

    private Integer formId;

    private String comment;

    private Integer lv;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApproveUserId() {
        return approveUserId;
    }

    public void setApproveUserId(Integer approveUserId) {
        this.approveUserId = approveUserId;
    }

    public Integer getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Integer isApprove) {
        this.isApprove = isApprove;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getLv() {
        return lv;
    }

    public void setLv(Integer lv) {
        this.lv = lv;
    }
}