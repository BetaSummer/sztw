package betahouse.service.financial;

import betahouse.mapper.ClubActivityFormMapper;
import betahouse.mapper.ClubFinancialFlowMapper;
import betahouse.mapper.ClubMapper;
import betahouse.model.Club;
import betahouse.model.ClubFinancialFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by x1654 on 2017/7/14.
 */
@Service
public class ClubFinancialFlowServiceImpl implements ClubFinancialFlowService{

    @Autowired
    private ClubFinancialFlowMapper clubFinancialFlowMapper;

    @Autowired
    private ClubMapper clubMapper;

    @Autowired
    private ClubActivityFormMapper clubActivityFormMapper;

    @Override
    public List<ClubFinancialFlow> listAll() {
        return clubFinancialFlowMapper.selectAll();
    }

    @Override
    public List<String[]> listClubFinancialFlowByClubId(int clubId) {
        List<String[]> listDTO = new ArrayList<>();
        List<ClubFinancialFlow> listDTO2 = clubFinancialFlowMapper.selectByClubId(clubId);
        for(ClubFinancialFlow c: listDTO2){
            String idDTO = String.valueOf(c.getId());
            String clubNameDTO = clubMapper.selectByPrimaryKey(c.getClubId()).getClubName();
            String activityNameDTO = clubActivityFormMapper.selectByPrimaryKey(c.getActivityId()).getActivityName();
            String incomeDTO = String.valueOf(c.getIncome());
            String costDTO = String.valueOf(c.getCost());
            listDTO.add(new String[]{idDTO, clubNameDTO, activityNameDTO, c.getComment(), incomeDTO, costDTO, c.getDate()});
        }
        return listDTO;
    }

    @Override
    public Map<String, int[]> listAllClubFinance() {
        Map<String, int[]> mapDTO = new HashMap<>();
        List<Club> listDTO = clubMapper.selectAll();
        for(Club c: listDTO){
            List<ClubFinancialFlow> listDTO2 = clubFinancialFlowMapper.selectByClubId(c.getId());
            int cost = 0;
            int income = 0;
            for(ClubFinancialFlow c2: listDTO2){
                cost+=c2.getCost();
                income+=c2.getIncome();
            }
            mapDTO.put(c.getClubName(), new int[]{c.getId(), cost, income, income-cost});
        }
        return mapDTO;
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
