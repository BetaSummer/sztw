package betahouse.model;

public class FormType {
    private Integer id;

    private Integer formType;

    private String formName;

    private Integer maxLv;

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

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName == null ? null : formName.trim();
    }

    public Integer getMaxLv() {
        return maxLv;
    }

    public void setMaxLv(Integer maxLv) {
        this.maxLv = maxLv;
    }
}