package betahouse.service.club;

import betahouse.model.Club;
import betahouse.model.ClubActivityForm;
import betahouse.model.UserInfo;

import java.util.List;

/**
 * Created by x1654 on 2017/7/4.
 */
public interface ClubActivityFormService {

    int commitForm(String club, String activityName, String activityPlace, String activityTime,
                   String activityPeople, String isApplyFine, String activityInfo, String applySelfMoney,
                   String applyReserveMoney, int fileId, UserInfo userInfo);

    ClubActivityForm getFormById(int id);

    ClubActivityForm getLastForm();

    int deleteFormById(int id);

    List<ClubActivityForm> listFormByClubName(String clubName);

    int updateFormById(int id, Club club);

    int deleteFormByClubId(int clubId);

    List<ClubActivityForm> listFormByClubId(int clubId);

}
