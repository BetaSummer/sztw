package betahouse.mapper;

import betahouse.model.OrganizationFinancialFlow;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface OrganizationFinancialFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrganizationFinancialFlow record);

    OrganizationFinancialFlow selectByPrimaryKey(Integer id);

    List<OrganizationFinancialFlow> selectAll();

    int updateByPrimaryKey(OrganizationFinancialFlow record);

    List<OrganizationFinancialFlow> selectByOrganization(Integer organizationId);
}