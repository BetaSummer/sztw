package betahouse.model;

public class ApprovePublicUtility {
    private Integer id;

    private Integer formId;

    private Integer isApprove;

    private String defendComment;

    private String electricComment;

    private Integer approveUserId;

    private String water;

    private String electric;

    private String start;

    private String end;

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

    public Integer getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Integer isApprove) {
        this.isApprove = isApprove;
    }

    public String getDefendComment() {
        return defendComment;
    }

    public void setDefendComment(String defendComment) {
        this.defendComment = defendComment == null ? null : defendComment.trim();
    }

    public String getElectricComment() {
        return electricComment;
    }

    public void setElectricComment(String electricComment) {
        this.electricComment = electricComment == null ? null : electricComment.trim();
    }

    public Integer getApproveUserId() {
        return approveUserId;
    }

    public void setApproveUserId(Integer approveUserId) {
        this.approveUserId = approveUserId;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water == null ? null : water.trim();
    }

    public String getElectric() {
        return electric;
    }

    public void setElectric(String electric) {
        this.electric = electric == null ? null : electric.trim();
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start == null ? null : start.trim();
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end == null ? null : end.trim();
    }
}