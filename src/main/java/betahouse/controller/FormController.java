package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.model.Club;
import betahouse.model.ClubActivityForm;
import betahouse.service.club.ClubActivityFormService;
import betahouse.service.club.ClubActivityStatusService;
import betahouse.service.club.ClubService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by x1654 on 2017/7/6.
 */
@Controller
@RequestMapping("/applyClubForm")
public class FormController extends BaseController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private ClubActivityFormService clubActivityFormService;

    @Autowired
    private ClubActivityStatusService clubActivityStatusService;

    @RequestMapping("/applyFormClubActivity")
    public String applyFormClubActivity(HttpServletRequest request, HttpServletResponse response, Model model){
    Club club = clubService.getClubByUserId(this.getCurrentUser(request).getId());
    model.addAttribute("club",club);
        return "clubActivity/clubForm";
    }

    @RequestMapping(value = "/commitClubActivity", method = RequestMethod.POST)
    public String commitClubActivity(HttpServletRequest request, HttpServletResponse response, Model model,
                                     @RequestParam String clubName, @RequestParam String activityName,
                                     @RequestParam String activityPlace, @RequestParam String activityPeople,
                                     @RequestParam String activityTime, @RequestParam String isApplyFine,
                                     @RequestParam String activityInfo, @RequestParam  String applySelfMoney,
                                     @RequestParam String applyReserveMoney){
        System.out.println(clubName);
        System.out.println(activityName);
        Club clubDTO = clubService.getClubByUserId(this.getCurrentUser(request).getId());
        clubActivityFormService.commitForm(clubName, getCurrentUser(request).getRealName(), activityName,
                activityPlace, activityTime, activityPeople, isApplyFine, activityInfo, applySelfMoney,
                applyReserveMoney, clubDTO.getId(), 0);
        int idDTO = clubActivityFormService.getLastInsertId();
        ClubActivityForm clubActivityFormDTO = clubActivityFormService.getFormById(idDTO);
        clubActivityStatusService.saveStatus(clubActivityFormDTO, getCurrentUser(request));
        return "user/index";
    }
}
