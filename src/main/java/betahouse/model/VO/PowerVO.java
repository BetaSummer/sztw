package betahouse.model.VO;

public class PowerVO {

    private Integer id;

    private String powerName;

    private Integer maxLv;

    private Integer permit;

    public Integer getPermit() {
        return permit;
    }

    public void setPermit(Integer permit) {
        this.permit = permit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public Integer getMaxLv() {
        return maxLv;
    }

    public void setMaxLv(Integer maxLv) {
        this.maxLv = maxLv;
    }
}
