package betahouse.service.power;

import betahouse.mapper.PowerMapper;
import betahouse.model.FormManager;
import betahouse.model.Power;
import betahouse.model.VO.PowerVO;
import betahouse.service.form.FormManagerService;
import com.alibaba.fastjson.JSON;
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
        List<PowerVO> listDTO = powerTypeService.listPowerVO();
        if(powerDTO!=null){
            List<Integer> powerListDTO = JSON.parseArray(powerDTO.getPower(), Integer.class);
            for(PowerVO p1: listDTO){
                for(int p2: powerListDTO){
                    if(p1.getId()==p2){
                        p1.setPermit(1);
                        if(p1.getMaxLv()!=0){
                            FormManager formManagerDTO = formManagerService.getFormManagerByApprover(userId);
                            List<Integer> lvListDTO = JSON.parseArray(formManagerDTO.getApproverForm(), Integer.class);
                            int formTypeDTO = powerTypeService.getPowerTypeByPowerId(p2).getFormType();
                            try {
                                p1.setPermit(lvListDTO.get(formTypeDTO - 1));
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
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
    public int updatePowerByUserId(int userId, String powerList, String permitList){
        if(powerList.equals("")){
            return -1;
        }
        Power powerDTO = powerMapper.selectByUserId(userId);
        List<Integer> powerListDTO = JSON.parseArray(powerList, Integer.class);
        List<Integer> permitListDTO = JSON.parseArray(permitList, Integer.class);
        List<Integer> powerListDTO2 = new ArrayList<>();
        if(powerDTO!=null){
            powerListDTO2 = JSON.parseArray(powerDTO.getPower(), Integer.class);
        }
        for(int i=0;i<powerListDTO.size();i++){
            if(permitListDTO.get(i)==0){
                powerListDTO2.remove(powerListDTO.get(i));
            }else if(permitListDTO.get(i)>0){
                powerListDTO2.add(powerListDTO.get(i));
            }
        }
        HashSet<Integer> hashSetDTO = new HashSet<>();
        hashSetDTO.addAll(powerListDTO2);
        powerListDTO2.clear();
        powerListDTO2.addAll(hashSetDTO);
        if(powerDTO==null){
            Power powerDTO2 = new Power();
            powerDTO2.setUserId(userId);
            powerDTO2.setPower(powerListDTO2.toString());
            return powerMapper.insert(powerDTO2);
        }
        powerDTO.setPower(powerListDTO2.toString());
        return powerMapper.updateByUserId(powerDTO);
    }
}
