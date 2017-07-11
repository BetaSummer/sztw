package betahouse.service.user;

import betahouse.mapper.PowerMapper;
import betahouse.model.Power;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        return JSON.parseArray(powerDTO.getPower(), Integer.class);
    }
}
