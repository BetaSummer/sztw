package betahouse.mapper;

import betahouse.model.Notice;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    Notice selectByPrimaryKey(Integer id);

    List<Notice> selectAll();

    int updateByPrimaryKey(Notice record);
}