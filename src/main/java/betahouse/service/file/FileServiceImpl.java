package betahouse.service.file;

import betahouse.core.Base.BaseFile;
import betahouse.mapper.FileMapper;
import betahouse.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by x1654 on 2017/7/8.
 */
@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private FileMapper fileMapper;

    @Override
    public int deleteByPrimaryKey(int id) {
        return 0;
    }

    @Override
    public int insert(String preName, String afterName, String folder) {
       String suffix =  preName.substring(preName.lastIndexOf("."));
        File fileDTO = new File();
        fileDTO.setPreName(preName);
        fileDTO.setAfterName(afterName+suffix);
        fileDTO.setFolder(folder);
        fileMapper.insert(fileDTO);
        return fileDTO.getId();
    }

    @Override
    public int updateByPrimaryKey(File file) {
        return 0;
    }

    @Override
    public File getFileById(int id) {
        return fileMapper.selectByPrimaryKey(id);
    }
}
