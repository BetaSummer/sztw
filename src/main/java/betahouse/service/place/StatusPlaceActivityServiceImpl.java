package betahouse.service.place;

import betahouse.mapper.StatusPlaceActivityMapper;
import betahouse.model.StatusPlaceActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusPlaceActivityServiceImpl implements StatusPlaceActivityService{

    @Autowired
    private StatusPlaceActivityMapper statusPlaceActivityMapper;

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
}
