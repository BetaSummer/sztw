package betahouse.service.user;

import betahouse.model.Power;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by x1654 on 2017/7/11.
 */
public interface PowerService {

    List<Integer> getPowerByUserId(int userId);

}
