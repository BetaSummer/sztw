package betahouse.controller;

import betahouse.core.Base.BaseController;
import betahouse.model.Admin;
import betahouse.model.User;
import betahouse.service.user.AdminService;
import betahouse.service.user.AdminServiceImpl;
import betahouse.service.user.UserService;
import betahouse.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by x1654 on 2017/7/3.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String admin(HttpServletRequest request, HttpServletResponse response, Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin = adminService.checkLogin(username, password);
        if(admin != null){
            model.addAttribute(admin);
            System.out.println("admin login");
//            return ajaxReturn(response,admin,"success",0);
            return "user/index";
        }
        User user = userService.checkLogin(username, password);
        if(user != null){
            model.addAttribute(user);
            System.out.println("user login");
//            return ajaxReturn(response,user,"success",0);
            return "user/index";
        }
        return null;
    }
}
