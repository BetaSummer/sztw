package betahouse.mapper;

import betahouse.model.OrganizationTimes;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface OrganizationTimesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrganizationTimes record);

    OrganizationTimes selectByPrimaryKey(Integer id);

    List<OrganizationTimes> selectAll();

    int updateByPrimaryKey(OrganizationTimes record);

    OrganizationTimes selectTimeByOrganizationId(Integer organizationId);
}