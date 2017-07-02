package betahouse.model;

public class Club {
    private Integer id;

    private String clubName;

    private Integer userId;

    private Integer selfMoney;

    private Integer reserveMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName == null ? null : clubName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSelfMoney() {
        return selfMoney;
    }

    public void setSelfMoney(Integer selfMoney) {
        this.selfMoney = selfMoney;
    }

    public Integer getReserveMoney() {
        return reserveMoney;
    }

    public void setReserveMoney(Integer reserveMoney) {
        this.reserveMoney = reserveMoney;
    }
}