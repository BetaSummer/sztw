package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.model.ClubActivityStatus;
import betahouse.model.FormManager;
import betahouse.service.club.ClubActivityStatusService;
import betahouse.service.club.FormManagerService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/listClubActivity")
    public String listClubActivity(HttpServletRequest request, HttpServletResponse response, Model model){
        int idDTO = getCurrentUser(request).getId();
        FormManager formManagerDTO = formManagerService.getFormManagerByApprover(idDTO);
        List<Integer> listDTO = JSON.parseArray(formManagerDTO.getApproverForm(), Integer.class);
        int lvDTO = listDTO.get(0);
        Map mapDTO = clubActivityStatusService.listStatusByTypeAndLv(1, lvDTO);
        model.addAttribute("data",mapDTO);
        return "clubActivity/formList";
    }
}
