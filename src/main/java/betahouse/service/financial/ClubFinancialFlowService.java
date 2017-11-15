package betahouse.service.financial;

import betahouse.model.ClubFinancialFlow;
import betahouse.model.VO.ClubFinanceVO;

import java.util.List;
import java.util.Map;

/**
 * Created by x1654 on 2017/7/14.
 */
public interface ClubFinancialFlowService {

    List<ClubFinancialFlow> listAll();

    List<ClubFinanceVO> listClubFinancialFlowByClubId(int clubId);

    List<ClubFinanceVO> listClubFinancialFlowByClubId_t(int clubId);

    Map<String, String[]> listAllClubFinance();

    ClubFinancialFlow getClubFinancialFlowByFormId(int formId);

    int insert(int clubId, int formId, float applySelfMoney, float applyReserveMoney, float income, String comment);

    int insert(int clubId, String comment, int handler, float change, float money);

    int deleteFinanceByClubId(int clubId);

}
