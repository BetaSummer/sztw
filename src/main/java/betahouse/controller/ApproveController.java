package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.model.Club;
import betahouse.model.ClubActivityApprove;
import betahouse.model.ClubActivityForm;
import betahouse.model.FormManager;
import betahouse.service.club.*;
import betahouse.service.user.UserInfoService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static betahouse.core.constant.FormConstant.*;

/**
 * Created by x1654 on 2017/7/12.
 */
@Controller
@RequestMapping(value = "/approveForm")
public class ApproveController extends BaseController{

    @Autowired
    private ClubActivityStatusService clubActivityStatusService;

    @Autowired
    private FormManagerService formManagerService;

    @Autowired
    private ClubActivityFormService clubActivityFormService;

    @Autowired
    private ClubActivityApproveService clubActivityApproveService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ClubService clubService;

    @RequestMapping(value = "/listClubActivity")
    public String listClubActivity(HttpServletRequest request, HttpServletResponse response, Model model){
        int idDTO = getCurrentUser(request).getId();
        FormManager formManagerDTO = formManagerService.getFormManagerByApprover(idDTO);
        List<Integer> listDTO = JSON.parseArray(formManagerDTO.getApproverForm(), Integer.class);
        int lvDTO = listDTO.get(0);
        Map mapDTO = clubActivityStatusService.listStatusByTypeAndLv(1, lvDTO);
        model.addAttribute("data",mapDTO);
        return "clubActivity/ApproveList";
    }

    @RequestMapping(value = "/getFormById")
    public String getFormById(HttpServletRequest request, HttpServletResponse response, Model model,
                              @RequestParam int id){
        ClubActivityForm clubActivityFormDTO = clubActivityFormService.getFormById(id);
        List<ClubActivityApprove> listDTO = clubActivityApproveService.listApproveByFormId(id);
        String[][] approverDTO = new String[4][2];
        approverDTO[0] = new String[]{userInfoService.getUserInfoById(clubService.getClubById(clubActivityFormDTO.getClubId()).getUserId()).getRealName(), ""};
        for(int i = 1; i<approverDTO.length; i++){
            approverDTO[i] = new String[]{CLUB_ACTIVITY_NOT_APPROVE[0], CLUB_ACTIVITY_NOT_APPROVE[1]};
        }
        for(int i = 0; i<listDTO.size(); i++){
            String realNameDTO = userInfoService.getUserInfoById(listDTO.get(i).getApproveUserId()).getRealName();
            approverDTO[i+1] = new String[]{realNameDTO, listDTO.get(i).getComment()};
        }
        Club clubDTO = clubService.getClubById(clubActivityFormDTO.getClubId());
        int[] moneyDTO = new int[]{clubDTO.getSelfMoney(), clubDTO.getReserveMoney()};
        model.addAttribute("clubActivityForm",clubActivityFormDTO);
        model.addAttribute("approver", approverDTO);
        model.addAttribute("money", moneyDTO);
        return "clubActivity/clubApprove";
    }

    @RequestMapping(value = "/approve")
    public String approve(HttpServletRequest request, HttpServletResponse response, Model model,
                          @RequestParam String formId, @RequestParam String comment, @RequestParam String applySelfMoney,
                          @RequestParam String applyReserveMoney, @RequestParam int isApprove){
        FormManager formManager = formManagerService.getFormManagerByApprover(getCurrentUser(request).getId());
        List<Integer>listDTO = JSON.parseArray(formManager.getApproverForm(), Integer.class);
        if(listDTO.get(0)==-1){
            return ajaxReturn(response, null, CLUB_ACTIVITY_APPROVE_FAIL, 1);
        }
        int formIdDTO = Integer.parseInt(formId);
        int approveLvDTO = clubActivityStatusService.getStatusByFormId(formIdDTO).getApproveLv();
        if(listDTO.get(0)!=approveLvDTO){
            return ajaxReturn(response, null, CLUB_ACTIVITY_APPROVE_FAIL, 1);
        }
        int applySelfMoneyDTO = Integer.parseInt(applySelfMoney);
        int applyReserveMoneyDTO = Integer.parseInt(applyReserveMoney);
        int returnNumDTO = clubActivityApproveService.saveApprove(getCurrentUser(request), isApprove, formIdDTO, comment,
                applySelfMoneyDTO, applyReserveMoneyDTO);
        if(returnNumDTO==-1){
            return ajaxReturn(response, null, CLUB_ACTIVITY_CLUB_NO_MONEY, 1);
        }
        return ajaxReturn(response, null, CLUB_ACTIVITY_APPROVE_SUCCESS, 0);
    }
}
