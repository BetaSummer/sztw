package betahouse.mapper;

import betahouse.model.OrganizationFinancialFlow;
import java.util.List;

public interface OrganizationFinancialFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrganizationFinancialFlow record);

    OrganizationFinancialFlow selectByPrimaryKey(Integer id);

    List<OrganizationFinancialFlow> selectAll();

    int updateByPrimaryKey(OrganizationFinancialFlow record);
}