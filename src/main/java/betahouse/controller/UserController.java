package betahouse.controller;


import betahouse.controller.Base.BaseController;
import betahouse.model.User;
import betahouse.model.UserInfo;
import betahouse.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static betahouse.core.constant.UserConstant.SESSION_CURRENT_USER;

/**
 * Created by x1654 on 2017/7/3.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;
        //return "redirect:/url";
//        @RequestMapping(value = "/login",method = RequestMethod.GET)
//        public String admin(HttpServletRequest request, HttpServletResponse response, Model model){
//            User user = userService.checkLogin(username, password);
//            if(user != null){
//                UserInfo userInfo = userInfoService.getUserInfoById(user.getId());
//                request.getSession().setAttribute(SESSION_CURRENT_USER_INFO, userInfo);
//                return ajaxReturn(response,null,"登陆成功",0);
//            }
//            return ajaxReturn(response,null,"用户不存在或用户名密码错误",1);
//        }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String admin(HttpServletRequest request, HttpServletResponse response, Model model,
                        @RequestParam String username,
                        @RequestParam String password){
        User user = userService.checkLogin(username, password);
        if(user != null){
            UserInfo userInfo = userInfoService.getUserInfoById(user.getId());
            request.getSession().setAttribute(SESSION_CURRENT_USER, userInfo);
            return ajaxReturn(response,null,"登陆成功",0);
        }
        return ajaxReturn(response,null,"用户不存在或用户名密码错误",1);
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
        return "user/userInfo";
    }
}
