package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.model.Club;
import betahouse.model.UserInfo;
import betahouse.service.club.ClubService;
import betahouse.service.information.AnnouncementService;
import betahouse.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static betahouse.core.constant.InformationConstant.PUBLISH_SUCCESS;
import static betahouse.core.constant.InformationConstant.SAVE_SUCCESS;

/**
 * Created by x1654 on 2017/7/7.
 */
@Controller
@RequestMapping(value = "/information", method = RequestMethod.POST)
public class InformationController extends BaseController{

    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping(value = "/publishAnnouncement", method = RequestMethod.POST)
    public String publishAnnouncement(HttpServletRequest request, HttpServletResponse response, Model model,
                                      @RequestParam String title, @RequestParam String comment){
        announcementService.sendAnnouncement(getCurrentUser(request).getId(), title, comment);
        return ajaxReturn(response, null, PUBLISH_SUCCESS, 0);
    }

    @RequestMapping(value = "/saveAnnouncement", method = RequestMethod.POST)
    public String saveAnnouncement(HttpServletRequest request, HttpServletResponse response, Model model,
                                   @RequestParam int id, @RequestParam String title, @RequestParam String comment){
        announcementService.saveAnnouncement(id, getCurrentUser(request).getId(), title, comment);
        return ajaxReturn(response, null, SAVE_SUCCESS, 0);
    }

    @RequestMapping(value = "/listUnpublishedAnnouncement")
    public String listUnpublishedAnnouncement(HttpServletRequest request, HttpServletResponse response, Model model){
        return ajaxReturn(response, announcementService.listUnpublishedAnnouncement(), "", 0);
    }

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

