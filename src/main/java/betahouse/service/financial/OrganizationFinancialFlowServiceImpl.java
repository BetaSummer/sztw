package betahouse.service.financial;

import betahouse.mapper.OrganizationFinancialFlowMapper;
import betahouse.model.FormPlaceActivity;
import betahouse.model.OrganizationFinancialFlow;
import betahouse.model.OrganizationMember;
import betahouse.service.organization.OrganizationMemberService;
import betahouse.service.organization.OrganizationService;
import betahouse.service.place.FormPlaceActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 5rKB5bPlusD on 8/25/2017
 */
@Service
public class OrganizationFinancialFlowServiceImpl implements OrganizationFinancialFlowService {

    @Autowired
    private OrganizationFinancialFlowMapper organizationFinancialFlowMapper;

    @Autowired
    private FormPlaceActivityService formPlaceActivityService;

    @Autowired
    private OrganizationMemberService organizationMemberService;

    @Autowired
    private OrganizationService organizationService;

    @Override
    public List<OrganizationFinancialFlow> listAll() {
        return organizationFinancialFlowMapper.selectAll();
    }

    @Override
    public List<OrganizationFinancialFlow> listByOrganization(int organizationId) {
        return organizationFinancialFlowMapper.selectByOrganization(organizationId);
    }

    @Override
    public int insert(int formId, String comment) {
        FormPlaceActivity formPlaceActivityDTO = formPlaceActivityService.getFormById(formId);
        float budgetDTO = formPlaceActivityDTO.getBudget();
        OrganizationMember organizationMemberDTO = organizationMemberService.getOrganizationByUserId(formPlaceActivityDTO.getUserId());
        int organizationIdDIO = organizationMemberDTO.getId();
        int leaderIdDTO = organizationService.getOrganizationById(organizationIdDIO).getLeaderId();
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        OrganizationFinancialFlow organizationFinancialFlowDTO = new OrganizationFinancialFlow();
        organizationFinancialFlowDTO.setOrganizationId(organizationIdDIO);
        organizationFinancialFlowDTO.setFormId(formId);
        organizationFinancialFlowDTO.setHandler(leaderIdDTO);
        organizationFinancialFlowDTO.setComment(comment);
        organizationFinancialFlowDTO.setIncome(0f);
        organizationFinancialFlowDTO.setCost(budgetDTO);
        organizationFinancialFlowDTO.setDate(sdfDTO.format(dateDTO));
        return organizationFinancialFlowMapper.insert(organizationFinancialFlowDTO);
    }

    @Override
    public int insert(int organizationId, int handler, int change, float money, String comment) {
        OrganizationFinancialFlow organizationFinancialFlowDTO = new OrganizationFinancialFlow();
        organizationFinancialFlowDTO.setOrganizationId(organizationId);
        organizationFinancialFlowDTO.setHandler(handler);
        organizationFinancialFlowDTO.setComment(comment);
        organizationFinancialFlowDTO.setIncome(0f);
        organizationFinancialFlowDTO.setCost(0f);
        if(-1==change){
            organizationFinancialFlowDTO.setCost(money);
        }else if(1==change){
            organizationFinancialFlowDTO.setIncome(money);
        }
        Date dateDTO = new Date();
        SimpleDateFormat sdfDTO = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        organizationFinancialFlowDTO.setDate(sdfDTO.format(dateDTO));
        return organizationFinancialFlowMapper.insert(organizationFinancialFlowDTO);
    }
}
