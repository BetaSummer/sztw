package betahouse.mapper;

import betahouse.model.Organization;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface OrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Organization record);

    Organization selectByPrimaryKey(Integer id);

    List<Organization> selectAll();

    int updateByPrimaryKey(Organization record);

    Organization selectByleaderId(Integer leaderId);
}