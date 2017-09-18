package betahouse.service.power;

import betahouse.model.VO.PowerVO;

import java.util.List;

/**
 * Created by x1654 on 2017/7/11.
 */
public interface PowerService {

    List<Integer> getPowerByUserId(int userId);

    List<PowerVO> getPowerVOByUserId(int userId);

    int insert(int id, String power);

    boolean  checkPower(int userId, int powerId);

    int updatePowerByUserId(int userId, String power);
}
