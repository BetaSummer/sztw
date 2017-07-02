package betahouse.model;

public class File {
    private Integer id;

    private String preName;

    private String afterName;

    private String folder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName == null ? null : preName.trim();
    }

    public String getAfterName() {
        return afterName;
    }

    public void setAfterName(String afterName) {
        this.afterName = afterName == null ? null : afterName.trim();
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder == null ? null : folder.trim();
    }
}