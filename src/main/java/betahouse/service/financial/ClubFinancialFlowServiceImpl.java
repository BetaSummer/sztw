package betahouse.service.financial;

import betahouse.mapper.ClubFinancialFlowMapper;
import betahouse.model.ClubFinancialFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by x1654 on 2017/7/14.
 */
@Service
public class ClubFinancialFlowServiceImpl implements ClubFinancialFlowService{

    @Autowired
    private ClubFinancialFlowMapper clubFinancialFlowMapper;

    @Override
    public List<ClubFinancialFlow> listClubFinancialFlowByClubId(int clubId) {
        return clubFinancialFlowMapper.selectByClubId(clubId);
    }

    @Override
    public ClubFinancialFlow getClubFinancialFlowByFormId(int formId) {
        return clubFinancialFlowMapper.selectByFormId(formId);
    }

    @Override
    public int insert(int clubId, int formId, int applySelfMoney, int applyReserveMoney, int income, String cmment) {
        ClubFinancialFlow clubFinancialFlowDTO = new ClubFinancialFlow();
        clubFinancialFlowDTO.setClubId(clubId);
        if(formId!=0){
            clubFinancialFlowDTO.setActivityId(formId);
        }
        clubFinancialFlowDTO.setCost(applySelfMoney+applyReserveMoney);
        clubFinancialFlowDTO.setIncome(income);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        clubFinancialFlowDTO.setDate(sdfDTO.format(dateDTO));
        clubFinancialFlowDTO.setComment(cmment);
        return clubFinancialFlowMapper.insert(clubFinancialFlowDTO);
    }
}
