package betahouse.service.file;
import betahouse.model.File;

/**
 * Created by x1654 on 2017/7/3.
 */
public interface FileService{

    int deleteByPrimaryKey(int id);

    int insert(String preName, String afterName, String folder);

    int updateByPrimaryKey(File file);

}
