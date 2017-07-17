package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.model.Club;
import betahouse.model.UserInfo;
import betahouse.service.club.ClubService;
import betahouse.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by x1654 on 2017/7/7.
 */
@Controller
@RequestMapping(value = "/information")
public class InformationController extends BaseController{

    @Autowired
    private ClubService clubService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/userManage")
    public String userManage(HttpServletRequest request, HttpServletResponse response, Model model){
        return "manage/userManage";
    }
    @RequestMapping(value = "/powerManage")
    public String powerManage(HttpServletRequest request, HttpServletResponse response, Model model){
        return "manage/powerManage";
    }
    @RequestMapping(value = "/financeT")
    public String financeT(HttpServletRequest request, HttpServletResponse response, Model model){
        return "manage/financeT";
    }
    @RequestMapping(value = "/financeB")
    public String financeB(HttpServletRequest request, HttpServletResponse response, Model model){
        return "manage/financeB";
    }
    @RequestMapping(value = "/clubManage")
    public String clubManage(HttpServletRequest request, HttpServletResponse response, Model model){
        return "manage/clubManage";
    }
    @RequestMapping(value = "/doMessage")
    public String doMessage(HttpServletRequest request, HttpServletResponse response, Model model){
        return "manage/doMessage";
    }
}

