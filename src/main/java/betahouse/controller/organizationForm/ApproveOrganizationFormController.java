package betahouse.controller.organizationForm;

import betahouse.controller.Base.BaseController;
import betahouse.mapper.OrganizationMapper;
import betahouse.mapper.OrganizationMemberMapper;
import betahouse.model.FormManager;
import betahouse.service.form.FormManagerService;
import betahouse.service.place.ApprovePlaceActivityService;
import betahouse.service.place.FormPlaceActivityService;
import betahouse.service.place.StatusPlaceActivityService;
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

import static betahouse.core.constant.CommonConstant.noPower;

@Controller
@RequestMapping("/approveOrganizationForm")
public class ApproveOrganizationFormController extends BaseController {
    @Autowired
    private StatusPlaceActivityService statusPlaceActivityService;

    @Autowired
    private FormManagerService formManagerService;

    @Autowired
    private FormPlaceActivityService formPlaceActivityService;

    @Autowired
    private ApprovePlaceActivityService approvePlaceActivityService;

    //表二审批列表
    @RequestMapping(value = "/approveList")
    public String listAllForm(HttpServletRequest request, HttpServletResponse response, Model model){
        int idDTO = getCurrentUser(request).getId();
        try {
            Map mapDTO = statusPlaceActivityService.listStatusByUserId(idDTO);
            model.addAttribute("data",mapDTO);
            return "organizationActivity/ApproveFormList";
        }catch (NullPointerException e){
            logger.error(getCurrentUser(request).getRealName()+"权限缺失");
            return noPower;
        }
    }

    //表二审批界面
    @RequestMapping(value = "/approve")
    public String areaApprove(HttpServletRequest request, HttpServletResponse response, Model model,
                              @RequestParam Integer id){
        model.addAttribute("data", formPlaceActivityService.getPlaceActivityTableVOById(id));
        return "organizationActivity/organizationApprove";
    }

    //表二审批
    public String saveApprove(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam int formId,
                              @RequestParam String comment, @RequestParam int isApprove){
        int userIdDTO = getCurrentUser(request).getId();
        approvePlaceActivityService.saveApprove(userIdDTO, isApprove, formId, comment);
        return "redirect:/index";
    }

}
