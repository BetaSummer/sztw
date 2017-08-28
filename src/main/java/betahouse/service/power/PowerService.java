package betahouse.service.power;

import betahouse.model.Power;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by x1654 on 2017/7/11.
 */
public interface PowerService {

    List<Integer> getPowerByUserId(int userId);

    int addPowerByUserId(int userId, int[] power);

    int deletePowerByUserId(int userId, int[] power);

    int insert(int id, String power);

    boolean  checkPower(int userId, int powerId);

}
