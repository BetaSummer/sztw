package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.core.Base.BaseFile;
import betahouse.core.office.HSSF;
import betahouse.mapper.ClubMapper;
import betahouse.model.Club;
import betahouse.model.ClubFinancialFlow;
import betahouse.model.VO.ClubFinance;
import betahouse.service.club.ClubService;
import betahouse.service.financial.ClubFinancialFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static betahouse.core.constant.FinanceConstant.CLUB_FINANCE_FIELD_NAME;
import static betahouse.core.constant.FolderNameConstant.FOLDER_OFFICE_EXCEL;
import static betahouse.core.constant.FolderNameConstant.FOLDER_OFFICE_EXCEL_SUFFIX;

/**
 * Created by x1654 on 2017/7/14.
 */
@Controller
@RequestMapping(value = "/finance")
public class FinanceController extends BaseController{

    @Autowired
    private ClubFinancialFlowService clubFinancialFlowService;

    @Autowired
    private ClubService  clubService;

    @RequestMapping(value = "/financeT")
    public String financeT(HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("clubFinancial", clubFinancialFlowService.listAllClubFinance());
        return "manage/financeT";
    }
    @RequestMapping(value = "/financeB")
    public String financeB(HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("club", clubService.listAll());
        return "manage/financeB";
    }

    @RequestMapping(value = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam String clubId){
        int clubIdDTO = Integer.parseInt(clubId);
        Club clubDTO = clubService.getClubById(clubIdDTO);
        List<ClubFinance> listDTO = clubFinancialFlowService.listClubFinancialFlowByClubId(clubIdDTO);
        HSSF hssf = new HSSF(clubDTO.getClubName(),clubDTO.getClubName());
        hssf.create(clubDTO.getClubName());
        hssf.insert(0, 0, 0, CLUB_FINANCE_FIELD_NAME, listDTO);
        BaseFile baseFile = new BaseFile();
        int statusDTO = baseFile.download(response, FOLDER_OFFICE_EXCEL+clubDTO.getClubName(),
                clubDTO.getClubName()+FOLDER_OFFICE_EXCEL_SUFFIX);
        if(statusDTO==1){
            this.error(request, response, model, statusDTO);
        }
    }
}
