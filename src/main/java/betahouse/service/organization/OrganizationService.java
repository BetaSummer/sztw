package betahouse.service.organization;

import betahouse.model.Organization;

public interface OrganizationService {

    Organization getOrganizationById(int id);

    Organization getOrganizationByLeaderId(int leaderId);

}
