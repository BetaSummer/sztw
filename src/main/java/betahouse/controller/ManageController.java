package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.service.club.ClubService;
import betahouse.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by x1654 on 2017/7/18.
 */
@Controller
@RequestMapping(value = "/manage")
public class ManageController extends BaseController{

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ClubService clubService;

    @RequestMapping(value = "/userManage")
    public String userManage(HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("userInfo", userInfoService.listAllUserInfo());
        return "manage/userManage";
    }
    @RequestMapping(value = "/powerManage")
    public String powerManage(HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("userInfo", userInfoService.listAllUserInfo());
        return "manage/powerManage";
    }

    @RequestMapping(value = "/clubManage")
    public String clubManage(HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("club", clubService.listClubAndChief());
        return "manage/clubManage";
    }
}
