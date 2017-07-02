package betahouse.mapper;

import betahouse.model.File;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(File record);

    File selectByPrimaryKey(Integer id);

    List<File> selectAll();

    int updateByPrimaryKey(File record);
}