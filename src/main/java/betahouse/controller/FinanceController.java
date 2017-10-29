package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.core.Base.BaseFile;
import betahouse.core.office.HSSF;
import betahouse.model.Club;
import betahouse.model.VO.ClubFinanceVO;
import betahouse.model.VO.ClubMoneyVO;
import betahouse.service.club.ClubService;
import betahouse.service.financial.ClubFinancialFlowService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static betahouse.core.constant.FinanceConstant.CLUB_FINANCE_CHANGE_MESSAGE;
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
    @RequestMapping(value = "/financeS")
    public String financeS(HttpServletRequest request, HttpServletResponse response, Model model){
        int clubId = clubService.getClubByUserId(getCurrentUser(request).getId()).getId();
        model.addAttribute("clubFinancial", clubFinancialFlowService.listClubFinancialFlowByClubId_t(clubId));
        model.addAttribute("clubId", clubId);
        //        return  ajaxReturn(response, model);
        return "manage/financeS";
    }

    @RequestMapping(value = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam String clubId){
        int clubIdDTO = Integer.parseInt(clubId);
        Club clubDTO = clubService.getClubById(clubIdDTO);
        List<ClubFinanceVO> listDTO = clubFinancialFlowService.listClubFinancialFlowByClubId(clubIdDTO);
        HSSF hssf = new HSSF(clubDTO.getClubName(),clubDTO.getClubName());
        hssf.create(clubDTO.getClubName());
        hssf.insert(0, 0, 0, CLUB_FINANCE_FIELD_NAME, listDTO);
        BaseFile baseFile = new BaseFile();
        int statusDTO = baseFile.download(response, FOLDER_OFFICE_EXCEL+clubDTO.getClubName(),
                clubDTO.getClubName()+FOLDER_OFFICE_EXCEL_SUFFIX);
        if(statusDTO==1){
            this.error(request, response, model, statusDTO);
        }
        baseFile.delete(FOLDER_OFFICE_EXCEL+clubDTO.getClubName(),
                clubDTO.getClubName()+FOLDER_OFFICE_EXCEL_SUFFIX);
    }

    @RequestMapping(value = "/changClubFinance")
    public String changClubFinance(HttpServletRequest request, HttpServletResponse response, Model model,
                                   @RequestParam String data,
                                   @RequestParam String comment){
        List<ClubMoneyVO> listDTO = JSON.parseArray(data, ClubMoneyVO.class);
        for(ClubMoneyVO c: listDTO){
            int idDTO = c.getId();
            int changeDTO = c.getChange();
            int selfReserveDTO = c.getSelfReserve();
            int moneyDTO = c.getMoney();
            int status = clubService.updateMoneyById(idDTO, changeDTO, selfReserveDTO, moneyDTO);
            if(-1==status){
                return ajaxReturn(response, null, CLUB_FINANCE_CHANGE_MESSAGE[1], 1);
            }
            int handlerDTO = getCurrentUser(request).getId();
            clubFinancialFlowService.insert(idDTO, comment, handlerDTO, changeDTO, moneyDTO);
        }

        return ajaxReturn(response, null, CLUB_FINANCE_CHANGE_MESSAGE[0], 0);
    }
}
