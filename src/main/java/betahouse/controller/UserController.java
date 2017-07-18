package betahouse.controller;


import betahouse.controller.Base.BaseController;
import betahouse.model.Club;
import betahouse.model.User;
import betahouse.model.UserInfo;
import betahouse.service.club.ClubService;
import betahouse.service.form.FormManagerService;
import betahouse.service.form.FormTypeService;
import betahouse.service.power.PowerService;
import betahouse.service.power.PowerTypeService;
import betahouse.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static betahouse.core.constant.UserConstant.*;

/**
 * Created by x1654 on 2017/7/3.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PowerService powerService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private FormTypeService formTypeService;

    @Autowired
    private PowerTypeService powerTypeService;

    @Autowired
    private FormManagerService formManagerService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String admin(HttpServletRequest request, HttpServletResponse response, Model model,
                        @RequestParam String username,
                        @RequestParam String password){
        User user = userService.checkLogin(username, password);
        if(user != null){
            UserInfo userInfo = userInfoService.getUserInfoById(user.getId());
            request.getSession().setAttribute(SESSION_CURRENT_USER, userInfo);
            request.getSession().setAttribute(SESSION_USER_POWER, powerService.getPowerByUserId(userInfo.getId()));
            return ajaxReturn(response,null,"",0);
        }
        return ajaxReturn(response,null,USER_LOGIN_FAILED,1);
    }
    @RequestMapping(value = "/logout")
    public String admin(HttpServletRequest request, HttpServletResponse response, Model model){
        if(this.getCurrentUser(request)!=null){
            request.getSession().setAttribute(SESSION_CURRENT_USER, null);
            return "redirect:/index";
        }
        return "redirect:/index";
    }
    @RequestMapping(value = "/getLv")
    public String getLv(HttpServletRequest request, HttpServletResponse response, Model model){
        // TODO: 2017/7/8 userinfo 没有lv了 根据userid 去managerform中 找到 formtype 返回 type的list
        //return ajaxReturn(response, getCurrentUser(request).getLv(), "用户Lv", 0);
        return null;
    }

    @RequestMapping(value = "/register")
    public String register(HttpServletRequest request, HttpServletResponse response, Model model){
        return "index/register";
    }

    @RequestMapping(value = "/userInfo")
    public String userInfo(HttpServletRequest request, HttpServletResponse response, Model model){
//        List<UserInfo> userInfosListDTO = userInfoService.listAllUserInfo();
//        List<Club> clubsListDTO = clubService.listAll();
//        Club[] clubsDTO = new Club[clubsListDTO.size()];
//        UserInfo[] userInfosDTO = new UserInfo[clubsListDTO.size()];
//        for(int i=0;i<clubsListDTO.size();i++) {
//            UserInfo userInfoDTO = userInfoService.getUserInfoById(clubsListDTO.get(i).getUserId());
//            clubsDTO[i] = clubsListDTO.get(i);
//            userInfosDTO[i] = userInfoDTO;
//        }
//        model.addAttribute("userInfo", userInfosListDTO);
//        model.addAttribute("club", clubsDTO);
//        model.addAttribute("chief", userInfosDTO);
//        model.addAttribute("power", formTypeService.listAll());
        model.addAttribute("user", getCurrentUser(request));
        return "user/userInfo";
    }

    @RequestMapping(value = "/listAllPower")
    public String listAllPower(HttpServletRequest request, HttpServletResponse response, Model model){
        return ajaxReturn(response, powerTypeService.listAll(), "", 0);
    }

    @RequestMapping(value = "/updatePower")
    public String updatePower(HttpServletRequest request, HttpServletResponse response, Model model,
                              @RequestParam int userId, @RequestParam int powerId, @RequestParam int lv){
        powerService.updatePowerByUserId(userId, powerId);
        if(0!=lv){
            formManagerService.updateFormManagerByApprover(userId, powerId, lv);
        }
        return ajaxReturn(response, null, USER_UPDATE_POWER_SUCCESS, 0);
    }

    @RequestMapping(value = "/getUserInfoById")
    public String getUserInfoById(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam int id){
        return ajaxReturn(response, userInfoService.getUserInfoById(id), "", 0);
    }
}
