package betahouse.service.organization;

import betahouse.mapper.OrganizationMapper;
import betahouse.model.Organization;
import betahouse.service.form.FormManagerService;
import betahouse.service.power.PowerService;
import betahouse.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PowerService powerService;

    @Autowired
    private FormManagerService formManagerService;

    @Override
    public Organization getOrganizationById(int id) {
        return organizationMapper.selectByPrimaryKey(id);
    }

    @Override
    public Organization getOrganizationByLeaderId(int leaderId) {
        return organizationMapper.selectByleaderId(leaderId);
    }

    @Override
    public int updateOrganizationById(int id, String name, int userId, String realName, String schoolId, String eMail, String tel) {
        if(!"".equals(name)){
            Organization organizationDTO = new Organization();
            organizationDTO.setId(id);
            organizationDTO.setOrganizationName(name);
            int oldUserId = organizationMapper.selectByPrimaryKey(id).getLeaderId();
            if(userId!=0&&oldUserId!=userId){
                organizationDTO.setLeaderId(userId);
                powerService.updatePowerByUserId(oldUserId, "[10,11]");
                formManagerService.updateFormManagerByApprover(oldUserId, "[12]", "[-1]");
                powerService.updatePowerByUserId(userId, "[10,11]");
                formManagerService.updateFormManagerByApprover(userId, "[12]","[1]");
            }
            organizationMapper.updateByPrimaryKey(organizationDTO);
        }
        if(!"".equals(realName)||!"".equals(schoolId)||!"".equals(eMail)||!"".equals(tel)){
            userInfoService.updateUserInfoById(userId, realName, schoolId, eMail, tel);
        }
        return 0;
    }
}
