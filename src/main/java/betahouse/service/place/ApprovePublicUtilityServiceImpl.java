package betahouse.service.place;

import betahouse.mapper.ApprovePlaceActivityMapper;
import betahouse.mapper.ApprovePublicUtilityMapper;
import betahouse.model.ApprovePlaceActivity;
import betahouse.model.ApprovePublicUtility;
import betahouse.model.StatusPlaceActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ApprovePublicUtilityServiceImpl implements ApprovePublicUtilityService{

    @Autowired
    private ApprovePublicUtilityMapper approvePublicUtilityMapper;

    @Autowired
    private StatusPlaceActivityService statusPlaceActivityService;

    @Override
    public int insert(int formId, int isApprove, String defendComment, String electricComment, int approveUserId, String water, String electric, String start, String end) {
        StatusPlaceActivity statusPlaceActivityDTO = statusPlaceActivityService.getStatusByFormId(formId);
        int lvDTO = 6;
        int publicStatus = 0;
        int status = 0;
        ApprovePublicUtility approvePublicUtilityDTO = new ApprovePublicUtility();
        approvePublicUtilityDTO.setFormId(formId);
        approvePublicUtilityDTO.setIsApprove(isApprove);
        approvePublicUtilityDTO.setDefendComment(defendComment);
        approvePublicUtilityDTO.setElectricComment(electricComment);
        approvePublicUtilityDTO.setApproveUserId(approveUserId);
        approvePublicUtilityDTO.setWater(water);
        approvePublicUtilityDTO.setElectric(electric);
        approvePublicUtilityDTO.setStart(start);
        approvePublicUtilityDTO.setEnd(end);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        if(1==isApprove){
            if(0==statusPlaceActivityDTO.getResourcesStatus()){
                status = 1;
            }
            lvDTO++;
            publicStatus = 2;
        }else if(0==isApprove){
            lvDTO = 99;
            publicStatus = 3;
            status = 2;
        }
        statusPlaceActivityService.updateStatusByFormId(formId, status, 0, publicStatus, lvDTO, sdfDTO.format(dateDTO));
        return approvePublicUtilityMapper.insert(approvePublicUtilityDTO);
    }
}
