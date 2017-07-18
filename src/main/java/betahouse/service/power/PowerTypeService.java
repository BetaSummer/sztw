package betahouse.service.power;

import betahouse.model.FormType;
import betahouse.model.PowerType;

import java.util.List;
import java.util.Map;

/**
 * Created by x1654 on 2017/7/18.
 */
public interface PowerTypeService {

    Map<PowerType, Integer> listAll();

}
