package betahouse.service.information;

import betahouse.model.Announcement;

import java.util.List;

/**
 * Created by x1654 on 2017/7/12.
 */
public interface AnnouncementService {

    List<Announcement> listAll();

    int sendAnnouncement(int fromId, String title, String comment, int fileId);

    int saveAnnouncement(int id, int fromId, String title, String comment, int fileId);

    List<Announcement> listUnpublishedAnnouncement();

    Announcement getAnnouncementById(int id);

}
