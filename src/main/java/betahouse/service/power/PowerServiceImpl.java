package betahouse.service.power;

import betahouse.mapper.PowerMapper;
import betahouse.model.FormManager;
import betahouse.model.FormType;
import betahouse.model.Power;
import betahouse.model.PowerType;
import betahouse.model.VO.PowerVO;
import betahouse.service.form.FormManagerService;
import betahouse.service.form.FormTypeService;
import com.alibaba.fastjson.JSON;
import org.codehaus.groovy.reflection.stdclasses.IntegerCachedClass;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by x1654 on 2017/7/11.
 */
@Service
public class PowerServiceImpl implements PowerService{

    @Autowired
    private PowerMapper powerMapper;

    @Autowired
    private PowerTypeService powerTypeService;

    @Autowired
    private FormManagerService formManagerService;

    @Override
    public List<Integer> getPowerByUserId(int userId) {
        Power powerDTO =powerMapper.selectByUserId(userId);
        if(powerDTO==null){
            List<Integer> listDTO = new ArrayList<>();
            listDTO.add(-1);
            return listDTO;
        }
        return JSON.parseArray(powerDTO.getPower(), Integer.class);
    }

    //根据用户id获取当前用户的权限并取出对应的权限名返回
    @Override
    public List<PowerVO> getPowerVOByUserId(int userId) {
        Power powerDTO = powerMapper.selectByUserId(userId);
        List<PowerVO> listDTO = new ArrayList<>();
        if(powerDTO==null){
            PowerVO powerVO = new PowerVO();
            powerVO.setId(-1);
            powerVO.setPowerName("");
            listDTO.add(powerVO);
            return listDTO;
        }
        List<Integer> powerListDTO = JSON.parseArray(powerDTO.getPower(), Integer.class);
        listDTO = powerTypeService.listAll();
        for(PowerVO p1: listDTO){
            for(int p2: powerListDTO){
                if(p1.getId()==p2){
                    p1.setPermit(1);
                    if(p1.getMaxLv()!=0){
                        FormManager formManagerDTO = formManagerService.getFormManagerByApprover(userId);
                        List<Integer> lvListDTO = JSON.parseArray(formManagerDTO.getApproverForm(), Integer.class);
                        int formTypeDTO = powerTypeService.getPowerTypeByPowerId(p2).getFormType();
                        p1.setMaxLv(lvListDTO.get(formTypeDTO-1));
                    }
                }
            }
        }
        return listDTO;
    }

    @Override
    public int insert(int id, String power) {
        Power powerDTO = new Power();
        powerDTO.setUserId(id);
        powerDTO.setPower(power);
        return powerMapper.insert(powerDTO);
    }

    @Override
    public boolean checkPower(int userId, int powerId) {
        Power powerDTO = powerMapper.selectByUserId(userId);
        List<Integer> listDTO = JSON.parseArray(powerDTO.getPower(), Integer.class);
        for (int power: listDTO) {
            if(power==powerId){
                return true;
            }
        }
        return false;
    }

    @Override
    public int updatePowerByUserId(int userId, String powerList){
        Power powerDTO = powerMapper.selectByUserId(userId);
        List<Integer> listDTO = JSON.parseArray(powerList, Integer.class);
        List<Integer> powerListDTO = JSON.parseArray(powerDTO.getPower(), Integer.class);
        for(int power: listDTO){
            boolean flag = false;
            for(int i=0;i<powerListDTO.size();i++){
                if(power==powerListDTO.get(i)){
                    flag = true;
                    powerListDTO.remove(power);
                }
            }
            if(!flag){
                powerListDTO.add(power);
            }
        }
        powerDTO.setPower(powerListDTO.toString());
        return powerMapper.updateByUserId(powerDTO);
    }

    public int updatePowerByUserIdFB(int userId, String powerVOList){
        Power powerDTO = powerMapper.selectByUserId(userId);
        List<PowerVO> listDTO = JSON.parseArray(powerVOList, PowerVO.class);
        List<Integer> powerListDTO = JSON.parseArray(powerDTO.getPower(), Integer.class);
        for(PowerVO p: listDTO){
            if(p.getPermit()==1){
                powerListDTO.add(p.getId());
            }else if(p.getPermit()==0){
                powerListDTO.remove(p.getId());
            }
        }
        HashSet<Integer> hashSetDTO = new HashSet<>();
        hashSetDTO.addAll(powerListDTO);
        powerListDTO.clear();
        powerListDTO.addAll(hashSetDTO);
        powerDTO.setPower(powerListDTO.toString());
        return powerMapper.updateByUserId(powerDTO);
    }
}
