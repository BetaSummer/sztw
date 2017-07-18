package betahouse.mapper;

import betahouse.model.ClubFinancialFlow;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ClubFinancialFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClubFinancialFlow record);

    ClubFinancialFlow selectByPrimaryKey(Integer id);

    List<ClubFinancialFlow> selectAll();

    int updateByPrimaryKey(ClubFinancialFlow record);

    List<ClubFinancialFlow> selectByClubId(int clubId);

    ClubFinancialFlow selectByFormId(int formId);
}