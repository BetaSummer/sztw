package betahouse.service.financial;

import betahouse.model.ClubFinancialFlow;

import java.util.List;

/**
 * Created by x1654 on 2017/7/14.
 */
public interface ClubFinancialFlowService {

    List<ClubFinancialFlow> listClubFinancialFlowByClubId(int clubId);

    ClubFinancialFlow getClubFinancialFlowByFormId(int formId);

    int insert(int clubId, int formId, int applySelfMoney, int applyReserveMoney,  int income, String comment);

}
