package betahouse.service.club;

import betahouse.model.Club;

import java.util.List;

/**
 * Created by x1654 on 2017/7/4.
 */
public interface ClubService {

    int updateMoneyById(int id, int applySelfMoney, int applyReserveMoney);

    Club getClubById(int id);

    Club getClubByUserId(int id);

}
