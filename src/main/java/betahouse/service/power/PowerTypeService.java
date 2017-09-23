package betahouse.service.power;

import betahouse.model.FormType;
import betahouse.model.PowerType;
import betahouse.model.VO.PowerVO;

import java.util.List;
import java.util.Map;

/**
 * Created by x1654 on 2017/7/18.
 */
public interface PowerTypeService {

    List<PowerVO> listPowerVO();

    List<Integer> listPower();

    PowerType getPowerTypeByPowerId(int powerId);

}
