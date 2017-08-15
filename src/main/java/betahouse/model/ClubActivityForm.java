package betahouse.model;

public class ClubActivityForm {
    private Integer id;

    private String applyDate;

    private String club;

    private String chiefName;

    private String chiefId;

    private String chiefTel;

    private String activityName;

    private String activityPlace;

    private String activityTime;

    private String activityPeople;

    private Integer isApplyFine;

    private String activityInfo;

    private Float applySelfMoney;

    private Float applyReserveMoney;

    private Float selfMoney;

    private Float reserveMoney;

    private Integer clubId;

    private Integer fileId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate == null ? null : applyDate.trim();
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club == null ? null : club.trim();
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName == null ? null : chiefName.trim();
    }

    public String getChiefId() {
        return chiefId;
    }

    public void setChiefId(String chiefId) {
        this.chiefId = chiefId == null ? null : chiefId.trim();
    }

    public String getChiefTel() {
        return chiefTel;
    }

    public void setChiefTel(String chiefTel) {
        this.chiefTel = chiefTel == null ? null : chiefTel.trim();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getActivityPlace() {
        return activityPlace;
    }

    public void setActivityPlace(String activityPlace) {
        this.activityPlace = activityPlace == null ? null : activityPlace.trim();
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime == null ? null : activityTime.trim();
    }

    public String getActivityPeople() {
        return activityPeople;
    }

    public void setActivityPeople(String activityPeople) {
        this.activityPeople = activityPeople == null ? null : activityPeople.trim();
    }

    public Integer getIsApplyFine() {
        return isApplyFine;
    }

    public void setIsApplyFine(Integer isApplyFine) {
        this.isApplyFine = isApplyFine;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo == null ? null : activityInfo.trim();
    }

    public Float getApplySelfMoney() {
        return applySelfMoney;
    }

    public void setApplySelfMoney(Float applySelfMoney) {
        this.applySelfMoney = applySelfMoney;
    }

    public Float getApplyReserveMoney() {
        return applyReserveMoney;
    }

    public void setApplyReserveMoney(Float applyReserveMoney) {
        this.applyReserveMoney = applyReserveMoney;
    }

    public Float getSelfMoney() {
        return selfMoney;
    }

    public void setSelfMoney(Float selfMoney) {
        this.selfMoney = selfMoney;
    }

    public Float getReserveMoney() {
        return reserveMoney;
    }

    public void setReserveMoney(Float reserveMoney) {
        this.reserveMoney = reserveMoney;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}