package betahouse.service.organization;

import betahouse.model.Organization;

public interface OrganizationService {

    Organization getOrganizationById(int id);

    Organization getOrganizationByLeaderId(int leaderId);

    int updateOrganizationById(int id, String name, int userId, String realName, String schoolId, String eMail, String tel);

}
