package betahouse.model;

public class Club {
    private Integer id;

    private String clubName;

    private Integer userId;

    private Float selfMoney;

    private Float reserveMoney;

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
}