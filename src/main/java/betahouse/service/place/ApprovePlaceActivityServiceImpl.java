package betahouse.service.place;

import betahouse.mapper.ApprovePlaceActivityMapper;
import betahouse.mapper.FormManagerMapper;
import betahouse.mapper.StatusPlaceActivityMapper;
import betahouse.mapper.UserInfoMapper;
import betahouse.model.ApprovePlaceActivity;
import betahouse.model.StatusPlaceActivity;
import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class ApprovePlaceActivityServiceImpl implements ApprovePlaceActivityService{

    @Autowired
    private ApprovePlaceActivityMapper approvePlaceActivityMapper;

    @Autowired
    private FormManagerMapper formManagerMapper;

    @Autowired
    private StatusPlaceActivityService statusPlaceActivityService;

    @Override
    public int saveApprove(int approveUserId, int isApprove, int formId, String comment) {
        String approveFormDTO = formManagerMapper.selectByApprover(approveUserId).getApproverForm();
        StatusPlaceActivity statusPlaceActivityDTO = statusPlaceActivityService.getStatusByFormId(formId);
        int lvDTO = JSON.parseArray(approveFormDTO, Integer.class).get(1);
        int publicStatusDTO = 0;
        int resourcesStatusDTO = 0;
        int statusDTO = 0;
        ApprovePlaceActivity approvePlaceActivityDTO = new ApprovePlaceActivity();
        approvePlaceActivityDTO.setApproveUserId(approveUserId);
        approvePlaceActivityDTO.setIsApprove(isApprove);
        approvePlaceActivityDTO.setFormId(formId);
        approvePlaceActivityDTO.setLv(lvDTO);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        approvePlaceActivityDTO.setDate(sdfDTO.format(dateDTO));
        if(0==isApprove){
            if(lvDTO==5){
                resourcesStatusDTO = 3;
            }else if(lvDTO==6){
                publicStatusDTO = 3;
            }
            lvDTO = 99;
            statusDTO = 2;
        }else if(1==isApprove){
            if(lvDTO==5){
                if(0==statusPlaceActivityDTO.getPublicStatus()||2==statusPlaceActivityDTO.getPublicStatus()){
                    statusDTO = 1;
                }
                resourcesStatusDTO = 2;
            }else if(lvDTO==6){
                if(0==statusPlaceActivityDTO.getResourcesStatus()||2==statusPlaceActivityDTO.getResourcesStatus()){
                    statusDTO = 1;
                }
                publicStatusDTO = 2;
            }
            lvDTO++;
        }
        statusPlaceActivityService.updateStatusByFormId(formId, statusDTO, resourcesStatusDTO, publicStatusDTO, lvDTO, sdfDTO.format(dateDTO));
        return approvePlaceActivityMapper.insert(approvePlaceActivityDTO);
    }

    @Override
    public List<ApprovePlaceActivity> listApproveByFormId(int formId) {
        return approvePlaceActivityMapper.selectByFormId(formId);
    }

}
