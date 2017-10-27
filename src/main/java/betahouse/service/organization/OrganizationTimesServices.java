package betahouse.service.organization;

import betahouse.model.OrganizationTimes;

/**
 * Created by 5rKB5bPlusD on 10/4/2017
 */
public interface OrganizationTimesServices {

    OrganizationTimes getTimeByOrganizationId(int organizationId);

    int insert(int organizationId, int time);

    int addTime(int id);

}
