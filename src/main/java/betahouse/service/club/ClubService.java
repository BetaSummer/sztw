package betahouse.service.club;

import betahouse.model.Club;
import betahouse.model.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by x1654 on 2017/7/4.
 */
public interface ClubService {

    int updateMoneyById(int id, int applySelfMoney, int applyReserveMoney);

    Club getClubById(int id);

    Club getClubByUserId(int id);

    List<Club> listAll();

    Map<String, UserInfo> listClubAndChief();

}
