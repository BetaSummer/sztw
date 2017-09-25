package betahouse.controller;


import betahouse.controller.Base.BaseController;
import betahouse.model.User;
import betahouse.model.UserInfo;
import betahouse.model.VO.UserListVO;
import betahouse.service.club.ClubService;
import betahouse.service.form.FormManagerService;
import betahouse.service.form.FormTypeService;
import betahouse.service.power.PowerService;
import betahouse.service.power.PowerTypeService;
import betahouse.service.user.UserInfoService;
import betahouse.service.user.UserService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    @RequestMapping(value = "/register")
    public String register(HttpServletRequest request, HttpServletResponse response, Model model){
        return "index/register";
    }

    @RequestMapping(value = "/userInfo")
    public String userInfo(HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("user", getCurrentUser(request));
        return "user/userInfo";
    }

    //根据用户id获取所有权限以及拥有的权限
    @RequestMapping(value = "/listAllPower")
    public String listAllPower(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam int userId){
        model.addAttribute("power", powerService.getPowerVOByUserId(userId));
        model.addAttribute("userId", userId);
        return "index/showPower";
    }

    @RequestMapping(value = "/updatePower")
    public String updatePower(HttpServletRequest request, HttpServletResponse response, Model model,
                              @RequestParam int userId, @RequestParam String powerList, @RequestParam String permitList, @RequestParam String lvList){
        powerService.updatePowerByUserId(userId, powerList, permitList);
        formManagerService.updateFormManagerByApprover(userId, powerList, lvList);
        return ajaxReturn(response, null, USER_UPDATE_POWER_SUCCESS, 0);
    }

    @RequestMapping(value = "/updateUserInfo")
    public String getUserInfoById(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam int id){
        return ajaxReturn(response, userInfoService.getUserInfoById(id), "", 0);
    }

    @RequestMapping(value = "/listAllUser")
    public String listAllUser(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam int id){
        List<User> userListDTO = userService.listAllUser();
        List<UserListVO> userListVOList = new ArrayList<>();
        for(User u: userListDTO){
            UserInfo userInfoDTO = userInfoService.getUserInfoById(u.getId());
            UserListVO userListVO = new UserListVO();
            userListVO.setId(u.getId());
            userListVO.setRealName(userInfoDTO.getRealName());
            userListVOList.add(userListVO);
        }
        return ajaxReturn(response, userListVOList, "", 0);
    }
  
    @RequestMapping(value = "/addAccount")
    public String addAccount(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam String reg_username,
                             @RequestParam String reg_password, @RequestParam String reg_realname, @RequestParam String reg_school_id,
                             @RequestParam String reg_tel, @RequestParam String reg_email){
        int idDTO = userService.register(reg_username, reg_password);
        userInfoService.insert(idDTO, reg_realname, reg_school_id, reg_email, reg_tel);
        return "index/register";
    }
}
