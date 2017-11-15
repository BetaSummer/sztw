package betahouse.service.financial;

import betahouse.mapper.*;
import betahouse.model.Club;
import betahouse.model.ClubFinancialFlow;
import betahouse.model.VO.ClubFinanceVO;
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
    public List<ClubFinanceVO> listClubFinancialFlowByClubId(int clubId) {
        List<ClubFinanceVO> listDTO = new ArrayList<>();
        List<ClubFinancialFlow> listDTO2 = clubFinancialFlowMapper.selectByClubId(clubId);
        for(int i=0;i<listDTO2.size();i++){
            ClubFinanceVO clubFinanceVODTO = new ClubFinanceVO();
            clubFinanceVODTO.setId(i+1);
            clubFinanceVODTO.setComment(listDTO2.get(i).getComment());
            clubFinanceVODTO.setUserName(userInfoMapper.selectByPrimaryKey(listDTO2.get(i).getHandler()).getRealName());
            clubFinanceVODTO.setIncome(listDTO2.get(i).getIncome());
            clubFinanceVODTO.setCost(listDTO2.get(i).getCost());
            clubFinanceVODTO.setPayments(listDTO2.get(i).getIncome()-listDTO2.get(i).getCost());
            clubFinanceVODTO.setDate(listDTO2.get(i).getDate());
            listDTO.add(clubFinanceVODTO);
        }
        return listDTO;
    }
    @Override
    public List<ClubFinanceVO> listClubFinancialFlowByClubId_t(int clubId) {
        List<ClubFinanceVO> listDTO = new ArrayList<>();
        List<ClubFinancialFlow> listDTO2 = clubFinancialFlowMapper.selectByClubId(clubId);
        float total = 0;
        for(int i=0;i<listDTO2.size();i++){
            ClubFinanceVO clubFinanceVODTO = new ClubFinanceVO();
            clubFinanceVODTO.setId(i+1);
            clubFinanceVODTO.setComment(listDTO2.get(i).getComment());
            clubFinanceVODTO.setUserName(userInfoMapper.selectByPrimaryKey(listDTO2.get(i).getHandler()).getRealName());
            clubFinanceVODTO.setIncome(listDTO2.get(i).getIncome());
            clubFinanceVODTO.setCost(listDTO2.get(i).getCost());
            clubFinanceVODTO.setPayments(listDTO2.get(i).getIncome()-listDTO2.get(i).getCost());
            clubFinanceVODTO.setDate(listDTO2.get(i).getDate().split("日")[0]+"日");
            total += clubFinanceVODTO.getPayments();
            listDTO.add(clubFinanceVODTO);
        }
        ClubFinanceVO clubFinanceVODTO = new ClubFinanceVO();
        clubFinanceVODTO.setDate("总计");
        clubFinanceVODTO.setPayments(total);
        listDTO.add(clubFinanceVODTO);
        return listDTO;
    }

    @Override
    public Map<String, String[]> listAllClubFinance() {
        Map<String, String[]> mapDTO = new HashMap<>();
        List<Club> listDTO = clubMapper.selectAll();
        for(Club c: listDTO){
            List<ClubFinancialFlow> listDTO2 = clubFinancialFlowMapper.selectByClubId(c.getId());
            float cost = 0;
            float income = 0;
            for(ClubFinancialFlow c2: listDTO2){
                cost+=c2.getCost();
                income+=c2.getIncome();
            }
            mapDTO.put(c.getClubName(), new String[]{c.getId().toString(), String.format("%.2f",cost), String.format("%.2f",income), String.format("%.2f",income-cost)});
        }
        return mapDTO;
    }

    @Override
    public ClubFinancialFlow getClubFinancialFlowByFormId(int formId) {
        return clubFinancialFlowMapper.selectByFormId(formId);
    }

    @Override
    public int insert(int clubId, int formId, float applySelfMoney, float applyReserveMoney, float income, String cmment) {
        ClubFinancialFlow clubFinancialFlowDTO = new ClubFinancialFlow();
        clubFinancialFlowDTO.setClubId(clubId);
        if(formId!=0){
            clubFinancialFlowDTO.setActivityId(formId);
        }
        clubFinancialFlowDTO.setCost(applySelfMoney+applyReserveMoney);
        clubFinancialFlowDTO.setIncome(income);
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        clubFinancialFlowDTO.setDate(sdfDTO.format(dateDTO));
        clubFinancialFlowDTO.setComment(cmment);
        int userIdDTO = clubActivityApproveMapper.selectByLvAndFormId(2, formId).getApproveUserId();
        clubFinancialFlowDTO.setHandler(userInfoMapper.selectByPrimaryKey(userIdDTO).getId());
        return clubFinancialFlowMapper.insert(clubFinancialFlowDTO);
    }

    @Override
    public int insert(int clubId, String comment, int handler, float change, float money) {
        ClubFinancialFlow clubFinancialFlowDTO = new ClubFinancialFlow();
        clubFinancialFlowDTO.setClubId(clubId);
        clubFinancialFlowDTO.setComment(comment);
        clubFinancialFlowDTO.setHandler(handler);
        clubFinancialFlowDTO.setIncome(0f);
        clubFinancialFlowDTO.setCost(0f);
        if(-1==change){
            clubFinancialFlowDTO.setCost(money);
        }else if(1==change){
            clubFinancialFlowDTO.setIncome(money);
        }
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        clubFinancialFlowDTO.setDate(sdfDTO.format(dateDTO));
        return clubFinancialFlowMapper.insert(clubFinancialFlowDTO);
    }

    @Override
    public int deleteFinanceByClubId(int clubId) {
        return clubFinancialFlowMapper.deleteByClubId(clubId);
    }
}
