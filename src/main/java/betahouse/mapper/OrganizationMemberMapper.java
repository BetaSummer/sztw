package betahouse.mapper;

import betahouse.model.OrganizationMember;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface OrganizationMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrganizationMember record);

    OrganizationMember selectByPrimaryKey(Integer id);

    List<OrganizationMember> selectAll();

    int updateByPrimaryKey(OrganizationMember record);

    List<OrganizationMember> selectByOrganizationId(Integer organizationId);
}