package betahouse.service.information;

import betahouse.mapper.AnnouncementMapper;
import betahouse.model.Announcement;
import betahouse.service.power.PowerService;
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

    @Autowired
    private PowerService powerService;

    @Override
    public List<Announcement> listAll() {
        List<Announcement> listDTO = announcementMapper.selectAll();
        return listDTO;
    }

    @Override
    public int sendAnnouncement(int fromId, String title, String comment, int fileId) {
        Announcement announcementDTO = new Announcement();
        announcementDTO.setFromId(fromId);
        announcementDTO.setTitle(title);
        announcementDTO.setComment(comment);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy/MM/dd");
        announcementDTO.setDate(sdfDTO.format(dateDTO));
        announcementDTO.setStatus(1);
        return announcementMapper.insert(announcementDTO);
    }

    @Override
    public int saveAnnouncement(int id, int fromId, String title, String comment, int fileId) {
        Announcement announcementDTO = new Announcement();
        announcementDTO.setFromId(fromId);
        announcementDTO.setTitle(title);
        announcementDTO.setComment(comment);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy/MM/dd");
        announcementDTO.setDate(sdfDTO.format(dateDTO));
        announcementDTO.setStatus(0);
        if(id==0){
            return announcementMapper.insert(announcementDTO);
        }
        announcementDTO.setId(id);
        return announcementMapper.updateByPrimaryKey(announcementDTO);
    }

    @Override
    public List<Announcement> listUnpublishedAnnouncement() {
        return announcementMapper.selectByStatus(0);
    }

    @Override
    public Announcement getAnnouncementById(int id) {
        return announcementMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Announcement> listAnnouncementByPower(int userId) {
        if(powerService.checkPower(userId, 15)){
            listAll();
        }
        if(powerService.checkPower(userId, 2)){
            return announcementMapper.selectByFromId(userId);
        }
        return null;
    }

    @Override
    public int deleteAnnouncementById(int id) {
        return announcementMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int editAnnouncement(int id, int fromId, String title, String comment, int fileId) {
        Announcement announcementDTO = new Announcement();
        announcementDTO.setFromId(fromId);
        announcementDTO.setTitle(title);
        announcementDTO.setComment(comment);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy/MM/dd");
        announcementDTO.setDate(sdfDTO.format(dateDTO));
        announcementDTO.setStatus(1);
        announcementDTO.setId(id);
        return announcementMapper.updateByPrimaryKey(announcementDTO);
    }
}
