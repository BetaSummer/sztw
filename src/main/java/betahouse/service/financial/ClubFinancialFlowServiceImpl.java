package betahouse.service.financial;

import betahouse.mapper.*;
import betahouse.model.Club;
import betahouse.model.ClubFinancialFlow;
import betahouse.model.VO.ClubFinance;
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

    @Autowired
    private ClubActivityApproveMapper clubActivityApproveMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<ClubFinancialFlow> listAll() {
        return clubFinancialFlowMapper.selectAll();
    }

    @Override
    public List<ClubFinance> listClubFinancialFlowByClubId(int clubId) {
        List<ClubFinance> listDTO = new ArrayList<>();
        List<ClubFinancialFlow> listDTO2 = clubFinancialFlowMapper.selectByClubId(clubId);
        for(int i=0;i<listDTO2.size();i++){
            ClubFinance clubFinanceDTO = new ClubFinance();
            clubFinanceDTO.setId(i+1);
            clubFinanceDTO.setComment(listDTO2.get(i).getComment());
            clubFinanceDTO.setUserName(userInfoMapper.selectByPrimaryKey(listDTO2.get(i).getHandler()).getRealName());
            clubFinanceDTO.setIncome(listDTO2.get(i).getIncome());
            clubFinanceDTO.setCost(listDTO2.get(i).getCost());
            clubFinanceDTO.setPayments(listDTO2.get(i).getIncome()-listDTO2.get(i).getCost());
            clubFinanceDTO.setDate(listDTO2.get(i).getDate());
            listDTO.add(clubFinanceDTO);
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
        int userIdDTO = clubActivityApproveMapper.selectByLvAndFormId(2, formId).getApproveUserId();
        clubFinancialFlowDTO.setHandler(userInfoMapper.selectByPrimaryKey(userIdDTO).getId());
        return clubFinancialFlowMapper.insert(clubFinancialFlowDTO);
    }

    @Override
    public int insert(int clubId, String comment, int handler, int change, int money) {
        ClubFinancialFlow clubFinancialFlowDTO = new ClubFinancialFlow();
        clubFinancialFlowDTO.setClubId(clubId);
        clubFinancialFlowDTO.setComment(comment);
        clubFinancialFlowDTO.setHandler(handler);
        if(-1==change){
            clubFinancialFlowDTO.setCost(money);
            clubFinancialFlowDTO.setIncome(0);
        }else if(1==change){
            clubFinancialFlowDTO.setIncome(money);
            clubFinancialFlowDTO.setCost(0);
        }
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        clubFinancialFlowDTO.setDate(sdfDTO.format(dateDTO));
        return clubFinancialFlowMapper.insert(clubFinancialFlowDTO);
    }
}
