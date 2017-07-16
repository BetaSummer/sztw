package betahouse.mapper;

import betahouse.model.Announcement;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AnnouncementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Announcement record);

    Announcement selectByPrimaryKey(Integer id);

    List<Announcement> selectAll();

    int updateByPrimaryKey(Announcement record);
}