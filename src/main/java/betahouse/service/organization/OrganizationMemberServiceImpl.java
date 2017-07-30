package betahouse.service.organization;

import betahouse.mapper.OrganizationMemberMapper;
import betahouse.model.OrganizationMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrganizationMemberServiceImpl implements OrganizationMemberService{

    @Autowired
    private OrganizationMemberMapper organizationMemberMapper;

    @Override
    public List<OrganizationMember> listMember(int organizationId) {
        return organizationMemberMapper.selectByOrganizationId(organizationId);
    }
}
