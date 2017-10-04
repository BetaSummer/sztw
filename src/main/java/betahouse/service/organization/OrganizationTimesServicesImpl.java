package betahouse.service.organization;

import betahouse.mapper.OrganizationTimesMapper;
import betahouse.model.OrganizationTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 5rKB5bPlusD on 10/4/2017
 */
@Service
public class OrganizationTimesServicesImpl implements OrganizationTimesServices{

    @Autowired
    private OrganizationTimesMapper organizationTimesMapper;

    @Override
    public OrganizationTimes getTimeByOrganizationId(int organizationId) {
        return organizationTimesMapper.selectTimeByOrganizationId(organizationId);
    }

    @Override
    public int insert(int organizationId, int time) {
        OrganizationTimes organizationTimesDTO = new OrganizationTimes();
        organizationTimesDTO.setOrganizationId(organizationId);
        organizationTimesDTO.setTimes(time);
        return organizationTimesMapper.insert(organizationTimesDTO);
    }

    @Override
    public int addTime(int id) {
        OrganizationTimes organizationTimesDTO = organizationTimesMapper.selectByPrimaryKey(id);
        organizationTimesDTO.setTimes(organizationTimesDTO.getTimes()+1);
        return organizationTimesMapper.updateByPrimaryKey(organizationTimesDTO);
    }
}
