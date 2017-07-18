package betahouse.service.club;

import betahouse.mapper.ClubMapper;
import betahouse.mapper.UserInfoMapper;
import betahouse.model.Club;
import betahouse.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by x1654 on 2017/7/4.
 */
@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubMapper clubMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Club getClubByUserId(int userId) {
        return clubMapper.selectByUserId(userId);
    }

    @Override
    public List<Club> listAll() {
        return clubMapper.selectAll();
    }

    @Override
    public Map<String, UserInfo> listClubAndChief() {
        Map<String, UserInfo> mapDTO = new HashMap<>();
        List<Club> listDTO = clubMapper.selectAll();
        for(Club c: listDTO){
            UserInfo userInfoDTO = userInfoMapper.selectByPrimaryKey(c.getUserId());
            mapDTO.put(c.getClubName(), userInfoDTO);
        }
        return mapDTO;
    }

    @Override
    public int updateMoneyById(int id, int applySelfMoney, int applyReserveMoney) {
        Club clubDTO = clubMapper.selectByPrimaryKey(id);
        int selfMoneyDTO = clubDTO.getSelfMoney()-applySelfMoney;
        int reserveMoneyDTO = clubDTO.getReserveMoney()-applyReserveMoney;
        if(selfMoneyDTO<0||reserveMoneyDTO<0){
            return -1;
        }
        Club clubDTO2 = new Club();
        clubDTO2.setId(id);
        clubDTO2.setSelfMoney(selfMoneyDTO);
        clubDTO2.setReserveMoney(reserveMoneyDTO);
        return clubMapper.updateByPrimaryKey(clubDTO2);
    }

    @Override
    public Club getClubById(int id) {
        return clubMapper.selectByPrimaryKey(id);
    }
}
