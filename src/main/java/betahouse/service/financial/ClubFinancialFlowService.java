package betahouse.service.financial;

import betahouse.model.ClubFinancialFlow;

import java.util.List;
import java.util.Map;

/**
 * Created by x1654 on 2017/7/14.
 */
public interface ClubFinancialFlowService {

    List<ClubFinancialFlow> listAll();

    List<String[]> listClubFinancialFlowByClubId(int clubId);

    Map<String, int[]> listAllClubFinance();

    ClubFinancialFlow getClubFinancialFlowByFormId(int formId);

    int insert(int clubId, int formId, int applySelfMoney, int applyReserveMoney,  int income, String comment);

}
