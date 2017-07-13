package betahouse.service.information;

import betahouse.mapper.AnnouncementMapper;
import betahouse.model.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by x1654 on 2017/7/12.
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService{

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public List<Announcement> listAll() {
        List<Announcement> listDTO = announcementMapper.selectAll();
        return listDTO;
    }
}
