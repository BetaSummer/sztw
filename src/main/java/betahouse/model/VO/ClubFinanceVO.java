package betahouse.model.VO;

/**
 * Created by x1654 on 2017/7/18.
 */
public class ClubFinanceVO {

    private Integer id;

    private String comment;

    private String userName;

    private Float income;

    private Float cost;

    private Float payments;

    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getPayments() {
        return payments;
    }

    public void setPayments(Float payments) {
        this.payments = payments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
