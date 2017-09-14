package betahouse.service.financial;

import betahouse.model.OrganizationFinancialFlow;

import java.util.List;

/**
 * Created by 5rKB5bPlusD on 8/25/2017
 */
public interface OrganizationFinancialFlowService {

    List<OrganizationFinancialFlow> listAll();

    List<OrganizationFinancialFlow> listByOrganization(int organizationId);

    int insert(int formId, String comment);

    int insert(int organizationId, int handler, int change, float money, String comment);

}
