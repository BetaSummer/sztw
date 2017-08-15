package betahouse.service.power;

import betahouse.mapper.PowerMapper;
import betahouse.model.Power;
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

    @Override
    public int updatePowerByUserId(int userId, int power) {
        Power powerDTO = powerMapper.selectByUserId(userId);
        List<Integer> listDTO = JSON.parseArray(powerDTO.getPower(), Integer.class);
        listDTO.add(power);
        listDTO = new ArrayList<Integer>(new HashSet<Integer>(listDTO));
        powerDTO.setPower(listDTO.toString());
        return powerMapper.updateByUserId(powerDTO);
    }

    @Override
    public int deletePowerByUserId(int userId, int[] power) {
        Power powerDTO = powerMapper.selectByUserId(userId);
        List<Integer> listDTO = JSON.parseArray(powerDTO.getPower(), Integer.class);
        for(int p: power){
            for(int i=0;i<listDTO.size();i++){
                if(listDTO.get(i)==p){
                    listDTO.remove(i);
                }
            }
        }
        powerDTO.setPower(listDTO.toString());
        return powerMapper.updateByUserId(powerDTO);
    }

    @Override
    public int insert(int id, String power) {
        Power powerDTO = new Power();
        powerDTO.setUserId(id);
        powerDTO.setPower(power);
        return powerMapper.insert(powerDTO);
    }
}
