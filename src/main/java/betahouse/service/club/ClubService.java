package betahouse.service.club;

import betahouse.model.Club;
import betahouse.model.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by x1654 on 2017/7/4.
 */
public interface ClubService {

    int updateMoneyById(int id, float applySelfMoney, float applyReserveMoney);

    Club getClubById(int id);

    Club getClubByUserId(int id);

    List<Club> listAll();

    Map<String, UserInfo> listClubAndChief();

    int updateMoneyById(int id, float change, float selfReserve, float money);

    int createClub(String folderName, String fileName);

    int deleteClubById(int clubId);

    int updateClubById(int clubId, String clubName,int userId, String realName, String schoolId, String eMail, String tel);
}
