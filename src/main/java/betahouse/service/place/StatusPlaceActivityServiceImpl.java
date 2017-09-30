package betahouse.service.place;

import betahouse.mapper.FormManagerMapper;
import betahouse.mapper.FormPlaceActivityMapper;
import betahouse.mapper.StatusPlaceActivityMapper;
import betahouse.model.StatusPlaceActivity;
import betahouse.service.organization.OrganizationMemberService;
import betahouse.service.organization.OrganizationService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static betahouse.core.constant.FormConstant.PLACE_ACTIVITY_STATUS_1;
import static betahouse.core.constant.FormConstant.PLACE_ACTIVITY_STATUS_2;

@Service
public class StatusPlaceActivityServiceImpl implements StatusPlaceActivityService{

    @Autowired
    private StatusPlaceActivityMapper statusPlaceActivityMapper;

    @Autowired
    private FormManagerMapper formManagerMapper;

    @Autowired
    private FormPlaceActivityMapper formPlaceActivityMapper;

    @Autowired
    private ApprovePlaceActivityService approvePlaceActivityService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationMemberService organizationMemberService;

    @Override
    public int insert(int formId, int formUserId, int resourcesStatus, int publicStatus) {
        StatusPlaceActivity statusPlaceActivityDTO = new StatusPlaceActivity();
        statusPlaceActivityDTO.setFormId(formId);
        statusPlaceActivityDTO.setFormUserId(formUserId);
        statusPlaceActivityDTO.setResourcesStatus(resourcesStatus);
        statusPlaceActivityDTO.setPublicStatus(publicStatus);
        statusPlaceActivityDTO.setStatus(0);
        statusPlaceActivityDTO.setApproveLv(2);
        statusPlaceActivityDTO.setFormType(2);
        statusPlaceActivityMapper.insert(statusPlaceActivityDTO);

        int organizationIdDTO = organizationMemberService.getOrganizationByUserId(formUserId).getId();
        int leaderIdDTO = organizationService.getOrganizationById(organizationIdDTO).getLeaderId();
        if(formUserId==leaderIdDTO){
            approvePlaceActivityService.saveApprove(leaderIdDTO, 1, formId, "同意");
        }
        return 0;
    }

    @Override
    public int updateStatusByFormId(int formId, int status, int resourcesStatus, int publicStatus, int approveLv, String approveDate) {
        StatusPlaceActivity statusPlaceActivityDTO = new StatusPlaceActivity();
        statusPlaceActivityDTO.setStatus(status);
        statusPlaceActivityDTO.setResourcesStatus(resourcesStatus);
        statusPlaceActivityDTO.setPublicStatus(publicStatus);
        statusPlaceActivityDTO.setApproveLv(approveLv);
        statusPlaceActivityDTO.setFormId(formId);
        statusPlaceActivityDTO.setApproveDate(approveDate);
        return statusPlaceActivityMapper.updateStatusByFormId(statusPlaceActivityDTO);
    }

    @Override
    public StatusPlaceActivity getStatusByFormId(int formId) {
        return statusPlaceActivityMapper.selectByFormId(formId);
    }

    @Override
    public Map listStatusByUserId(int userId) {
        String approveFormDTO = formManagerMapper.selectByApprover(userId).getApproverForm();
        int lvSDTO = JSON.parseArray(approveFormDTO, Integer.class).get(1);
        List<StatusPlaceActivity> listDTO = null;
        if(lvSDTO==1) {
            listDTO = statusPlaceActivityMapper.selectByFormUserId(userId);
        }else if(lvSDTO<5){
            listDTO = statusPlaceActivityMapper.selectByLv(lvSDTO);
        }else if(lvSDTO==5){
            listDTO = statusPlaceActivityMapper.selectByLvAndResourcesStatus(lvSDTO, 1);
        }else if(lvSDTO==6){
            listDTO = statusPlaceActivityMapper.selectByLvAndPublicStatus(lvSDTO, 1);
        }
        Map<Integer, String[]> mapDTO = new HashMap<>();
        for(StatusPlaceActivity s: listDTO){
            mapDTO.put(s.getFormId(), new String[]{formPlaceActivityMapper.selectByPrimaryKey(s.getFormId()).getActivityName(),
                    PLACE_ACTIVITY_STATUS_1[s.getStatus()], PLACE_ACTIVITY_STATUS_2[s.getApproveLv()-1]});
        }
        return mapDTO;
    }

    @Override
    public Map listStatusOverUserId(int userId) {
        String approveFormDTO = formManagerMapper.selectByApprover(userId).getApproverForm();
        int lvSDTO = JSON.parseArray(approveFormDTO, Integer.class).get(1);
        List<StatusPlaceActivity> listDTO = statusPlaceActivityMapper.selectOverLv(lvSDTO);
        Map<Integer, String[]> mapDTO = new HashMap<>();
        for(StatusPlaceActivity s: listDTO){
            mapDTO.put(s.getFormId(), new String[]{formPlaceActivityMapper.selectByPrimaryKey(s.getFormId()).getActivityName(),
                    PLACE_ACTIVITY_STATUS_1[s.getStatus()], PLACE_ACTIVITY_STATUS_2[s.getApproveLv()-1]});
        }
        return mapDTO;
    }
}
