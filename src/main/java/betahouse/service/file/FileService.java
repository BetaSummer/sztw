package betahouse.service.file;
import betahouse.model.File;
import org.springframework.stereotype.Component;

/**
 * Created by x1654 on 2017/7/3.
 */
@Component
public interface FileService{

    int deleteByPrimaryKey(int id);

    int insert(String preName, String afterName, String folder);

    int updateByPrimaryKey(File file);

}
