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
    public int updateMoneyById(int id, int selfMoney, int reserveMoney) {
        Club clubDTO = new Club();
        clubDTO.setId(id);
        clubDTO.setSelfMoney(selfMoney);
        clubDTO.setReserveMoney(reserveMoney);
        return clubMapper.updateByPrimaryKey(clubDTO);
    }

    @Override
    public Club getClubById(int id) {
        return clubMapper.selectByPrimaryKey(id);
    }
}
