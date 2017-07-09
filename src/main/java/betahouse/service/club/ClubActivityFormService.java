package betahouse.service.club;

import betahouse.model.ClubActivityForm;

import java.util.List;

/**
 * Created by x1654 on 2017/7/4.
 */
public interface ClubActivityFormService {

    int commitForm(String club, String chiefName, String activityName, String activityPlace, String activityTime,
               String activityPeople, String isApplyFine, String activityInfo, String applySelfMoney, String applyReserveMoney,
               int clubId, int fileId);

    ClubActivityForm getFormById(int id);

    ClubActivityForm getLastForm();

    int deleteFormById(int id);

    List<ClubActivityForm> listFormByClubName(String clubName);

}
