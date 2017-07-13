package betahouse.service.club;

import betahouse.mapper.ClubMapper;
import betahouse.model.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by x1654 on 2017/7/4.
 */
@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubMapper clubMapper;

    @Override
    public Club getClubByUserId(int userId) {
        return clubMapper.selectByUserId(userId);
    }

    @Override
    public int updateMoneyById(int id, int applySelfMoney, int applyReserveMoney) {
        Club clubDTO = clubMapper.selectByPrimaryKey(id);
        Club clubDTO2 = new Club();
        clubDTO2.setId(id);
        clubDTO2.setSelfMoney(clubDTO.getSelfMoney()-applySelfMoney);
        clubDTO2.setReserveMoney(clubDTO.getReserveMoney()-applyReserveMoney);
        return clubMapper.updateByPrimaryKey(clubDTO2);
    }

    @Override
    public Club getClubById(int id) {
        return clubMapper.selectByPrimaryKey(id);
    }
}
