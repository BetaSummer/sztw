package betahouse.service.club;

import betahouse.model.ClubActivityForm;
import betahouse.model.ClubActivityStatus;
import betahouse.model.User;
import betahouse.model.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by x1654 on 2017/7/3.
 */
public interface ClubActivityStatusService {

    List<ClubActivityStatus> listAll();

    ClubActivityStatus getStatusById(int id);

    ClubActivityStatus getStatusByFormId(int formId);

    int saveStatus(ClubActivityForm form, UserInfo userInfo);

    Map listStatusByFormUserId(int formUserId);

    int updateLvByFormId(int lv, int formId);

    Map listAllByLv(int lv);

}
