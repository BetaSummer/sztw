package betahouse.service.information;

import betahouse.mapper.AnnouncementMapper;
import betahouse.model.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    public int sendAnnouncement(int fromId, String title, String comment) {
        Announcement announcementDTO = new Announcement();
        announcementDTO.setFromId(fromId);
        announcementDTO.setTitle(title);
        announcementDTO.setComment(comment);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy/MM/dd");
        announcementDTO.setDate(sdfDTO.format(dateDTO));
        return announcementMapper.insert(announcementDTO);
    }
}
