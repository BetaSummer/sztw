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
    public int updateFormManagerByApprover(int approver, int formType, int lv) {
        FormManager formManagerDTO = formManagerMapper.selectByApprover(approver);
        List<Integer> listDTO = JSON.parseArray(formManagerDTO.getApproverForm(), Integer.class);
        if(listDTO.size()<formType){
            for(int i=listDTO.size();i<formType-1;i++){
                listDTO.add(i, -1);
            }
        }
        listDTO.add(formType-1, lv);
        String strDTO = JSON.toJSONString(listDTO);
        formManagerDTO.setApproverForm(strDTO);
        return formManagerMapper.updateByApprover(formManagerDTO);
    }
}
