package betahouse.service.organization;

import betahouse.mapper.OrganizationMapper;
import betahouse.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public Organization getOrganizationById(int id) {
        return organizationMapper.selectByPrimaryKey(id);
    }

    @Override
    public Organization getOrganizationByLeaderId(int leaderId) {
        return organizationMapper.selectByleaderId(leaderId);
    }
}
