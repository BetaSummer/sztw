package betahouse.service.power;

import betahouse.mapper.PowerTypeMapper;
import betahouse.model.Power;
import betahouse.model.PowerType;
import betahouse.service.form.FormTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by x1654 on 2017/7/18.
 */
@Service
public class PowerTypeServiceImpl implements PowerTypeService{

    @Autowired
    private PowerTypeMapper powerTypeMapper;

    @Autowired
    private FormTypeService formTypeService;

    @Override
    public Map<PowerType, Integer> listAll() {
        Map<PowerType, Integer> mapDTO = new HashMap<>();
        List<PowerType> listDTO = powerTypeMapper.selectAll();
        for(PowerType p: listDTO){
            int maxLv = 0;
            if(null!=p.getFormType()){
                maxLv = formTypeService.getFormTypeByFormType(p.getFormType()).getMaxLv();
            }
            mapDTO.put(p, maxLv);
        }
        return mapDTO;
    }
}
