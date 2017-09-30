package betahouse.controller.organizationForm;

import betahouse.controller.Base.BaseController;
import betahouse.mapper.OrganizationMapper;
import betahouse.mapper.OrganizationMemberMapper;
import betahouse.model.FormManager;
import betahouse.service.form.FormManagerService;
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
@RequestMapping("/applyOrganizationForm")
public class OrganizationFormController extends BaseController {
    @Autowired
    private OrganizationMemberMapper organizationMemberMapper;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private StatusPlaceActivityService statusPlaceActivityService;

    @Autowired
    private FormManagerService formManagerService;

    @Autowired
    private FormPlaceActivityService formPlaceActivityService;

    //表二查看列表
    @RequestMapping(value = "/listAllForm")
    public String listAllForm(HttpServletRequest request, HttpServletResponse response, Model model){
        int idDTO = getCurrentUser(request).getId();
        FormManager formManagerDTO = formManagerService.getFormManagerByApprover(idDTO);
        List<Integer> listDTO = JSON.parseArray(formManagerDTO.getApproverForm(), Integer.class);
        try {
            int lvDTO = listDTO.get(1);
            if(lvDTO == 1){
                Map mapDTO = statusPlaceActivityService.listStatusByUserId(idDTO);
                model.addAttribute("data",mapDTO);
                return "organizationActivity/FormList";
            }
            Map mapDTO = statusPlaceActivityService.listStatusOverUserId(idDTO);
            model.addAttribute("data",mapDTO);
            return "organizationActivity/FormList";
        }catch (NullPointerException e){
            logger.error(getCurrentUser(request).getRealName()+"权限缺失");
            return noPower;
        }
    }

    //表二详细界面
    @RequestMapping(value = "/view")
    public String areaApprove(HttpServletRequest request, HttpServletResponse response, Model model,
                              @RequestParam Integer id){
        model.addAttribute("data", formPlaceActivityService.getPlaceActivityTableVOById(id));
        return "organizationActivity/organizationView";
    }
    //表二申请界面
    @RequestMapping(value = "/apply")
    public String ariaForm(HttpServletRequest request, HttpServletResponse response, Model model){
        int idDTO = organizationMemberMapper.selectByUserId(getCurrentUser(request).getId()).getOrganizationId();
        String organizationNameDTO = organizationMapper.selectByPrimaryKey(idDTO).getOrganizationName();
        model.addAttribute("organizationName", organizationNameDTO);
        model.addAttribute("user", getCurrentUser(request));
        return "organizationActivity/organizationForm";
    }

    //表二提交表单
    @RequestMapping(value = "/commit")
    public String commitForm(HttpServletRequest request, HttpServletResponse response, Model model,
                             @RequestParam(name = "tel") String tel,
                             @RequestParam(name = "activityName") String activityName,
                             @RequestParam(name = "activityPlace") String activityPlace,
                             @RequestParam(name = "activityDate") String activityDate,
                             @RequestParam(name = "list") String list,
                             @RequestParam(name = "content") String content,
                             @RequestParam(name = "budget") String budget,
                             @RequestParam(name = "method") String method,
                             @RequestParam(name = "resourcesStatus") String resourcesStatus,
                             @RequestParam(name = "resources") String resources,
                             @RequestParam(name = "isWater") String isWater,
                             @RequestParam(name = "isElectric") String isElectric,
                             @RequestParam(name = "water") String water,
                             @RequestParam(name = "electric") String electric,
                             @RequestParam(name = "start") String start,
                             @RequestParam(name = "end") String end){
        int resourcesStatusDTO = Integer.parseInt(resourcesStatus);
        list+=";"+resources;
        if("".equals(isWater)&&"".equals(isElectric)){
            formPlaceActivityService.commitForm(getCurrentUser(request).getId(), tel,  activityName, activityPlace, activityDate, list, content,
                    budget, method, resourcesStatusDTO);
        }else {
            formPlaceActivityService.commitForm(getCurrentUser(request).getId(), tel, activityName, activityPlace, activityDate, list, content,
                    budget, method, resourcesStatusDTO, water, electric, start, end);
        }
        return "redirect:/index";
    }
}
