package betahouse.service.organization;

import betahouse.model.OrganizationMember;

import java.util.List;

public interface OrganizationMemberService {

    List<OrganizationMember> listMember(int organizationId);

    OrganizationMember getOrganizationByUserId(int userId);

}
