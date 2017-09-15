package betahouse.service.power;

import betahouse.mapper.PowerTypeMapper;
import betahouse.model.Power;
import betahouse.model.PowerType;
import betahouse.model.VO.PowerVO;
import betahouse.service.form.FormTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<PowerVO> listAll() {
        List<PowerVO> powerVOList = new ArrayList<>();
        List<PowerType> listDTO = powerTypeMapper.selectAll();
        for(PowerType p: listDTO){
            int maxLv = 0;
            PowerVO powerVO = new PowerVO();
            powerVO.setId(p.getId());
            powerVO.setPowerName(p.getPowerName());
            if(null!=p.getFormType()){
                maxLv = formTypeService.getFormTypeByFormType(p.getFormType()).getMaxLv();
                powerVO.setMaxLv(maxLv);
            }
            powerVOList.add(powerVO);
        }
        return powerVOList;
    }

    @Override
    public PowerType getPowerTypeByPowerId(int powerId) {
        return powerTypeMapper.selectByPowerId(powerId);
    }
}
