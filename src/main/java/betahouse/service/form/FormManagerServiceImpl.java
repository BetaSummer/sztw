package betahouse.service.form;

import betahouse.mapper.FormManagerMapper;
import betahouse.model.FormManager;
import betahouse.service.power.PowerTypeService;
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

    @Autowired
    private PowerTypeService powerTypeService;

    @Override
    public FormManager getFormManagerByApprover(int approverId) {
        return formManagerMapper.selectByApprover(approverId);
    }

    @Override
    public int insertFormManager(int approver, String approverForm) {
        FormManager formManager = new FormManager();
        formManager.setApprover(approver);
        formManager.setApproverForm(approverForm);
        System.out.println("hhhh");
        System.out.println("hhhh");
        return formManagerMapper.insert(formManager);
    }

    @Override
    public int updateFormManagerByApprover(int approver, String powerList, String lvList) {
        FormManager formManagerDTO = formManagerMapper.selectByApprover(approver);
        List<Integer> listDTO = new ArrayList<>();
        if(formManagerDTO!=null){
            listDTO = JSON.parseArray(formManagerDTO.getApproverForm(), Integer.class);
        }
        List<Integer> powerListDTO = JSON.parseArray(powerList, Integer.class);
        List<Integer> lvListDTO = JSON.parseArray(lvList, Integer.class);
        for(int i=0;i<lvListDTO.size();i++){
            int lv = lvListDTO.get(i);
            if(lv!=0){
                int formTypeDTO = powerTypeService.getPowerTypeByPowerId(powerListDTO.get(i)).getFormType();

                if(listDTO.size()<formTypeDTO){
                    for(int j=listDTO.size();j<formTypeDTO-1;j++){
                        listDTO.add(j, -1);
                    }
                }
                listDTO.add(formTypeDTO-1, lv);
            }
        }
        String strDTO = JSON.toJSONString(listDTO);
        if(formManagerDTO==null){
            FormManager formManagerDTO2 = new FormManager();
            formManagerDTO2.setApproverForm(strDTO);
            formManagerDTO2.setApprover(approver);
            return formManagerMapper.insert(formManagerDTO2);
        }
        formManagerDTO.setApproverForm(strDTO);
        return formManagerMapper.updateByApprover(formManagerDTO);
    }

    @Override
    public List<FormManager> listFormManagerByFormTypeAndLv(int formType, int lv) {
        List<FormManager> listDTO = new ArrayList<>();
        List<FormManager> listDTO2 = formManagerMapper.selectAll();
        for(FormManager f: listDTO2){
            List<Integer> listDTO3 = JSON.parseArray(f.getApproverForm(), Integer.class);
            if(listDTO3.get(formType-1)==lv){
                listDTO.add(f);
            }
        }
        return listDTO;
    }

    @Override
    public int updateFormManagerByUserId(int userId, int formType, int lv) {
        FormManager formManagerDTO = getFormManagerByApprover(userId);
        List<Integer> listDTO = JSON.parseArray(formManagerDTO.getApproverForm(), Integer.class);
        listDTO.add(formType-1, lv);
        formManagerDTO.setApproverForm(listDTO.toString());
        return formManagerMapper.updateByPrimaryKey(formManagerDTO);
    }
}
