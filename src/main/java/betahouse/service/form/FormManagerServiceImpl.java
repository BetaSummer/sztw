package betahouse.service.form;

import betahouse.mapper.FormManagerMapper;
import betahouse.model.FormManager;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by x1654 on 2017/7/10.
 */
@Service
public class FormManagerServiceImpl implements FormManagerService{

    @Autowired
    private FormManagerMapper formManagerMapper;

    @Override
    public FormManager getFormManagerByApprover(int approverId) {
        return formManagerMapper.selectByApprover(approverId);
    }

    @Override
    public int insertFormManager() {
        FormManager formManager = new FormManager();
        return formManagerMapper.insert(formManager);
    }

    @Override
    public int updateFormManager(int approver) {
        FormManager formManager = new FormManager();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        String s = JSON.toJSONString(list);
        formManager.setApproverForm(s);
        formManager.setApprover(approver);
        return formManagerMapper.updateByApprover(formManager);
    }
}
