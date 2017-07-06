package betahouse.service.club;

import betahouse.model.ClubActivityForm;
import betahouse.model.ClubActivityStatus;
import betahouse.model.User;

import java.util.List;

/**
 * Created by x1654 on 2017/7/3.
 */
public interface ClubActivityStatusService {

    List<ClubActivityStatus> listAll();

    ClubActivityStatus getStatusById(int id);

    ClubActivityStatus getStatusByFormId(int formId);

    int saveStatus(ClubActivityForm form, User user);

    String[][] listStatusByFormUserId(int formUserId);

    int updateLvByFormId(User user, int formId);

    String[][] listAllByLv(User user);

}
