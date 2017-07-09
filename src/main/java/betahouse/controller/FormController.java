package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.core.Base.BaseFile;
import betahouse.model.Club;
import betahouse.model.ClubActivityForm;
import betahouse.service.club.ClubActivityFormService;
import betahouse.service.club.ClubActivityStatusService;
import betahouse.service.club.ClubService;
import betahouse.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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

    @Autowired
    private FileService fileService;

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
                                     @RequestParam String applyReserveMoney, @RequestParam("applyFile") MultipartFile file){
        BaseFile baseFile = new BaseFile();
        baseFile.upload(file,clubName,activityName,true);
        int fileIdDTO = fileService.insert(file.getOriginalFilename(), activityName, clubName);
        Club clubDTO = clubService.getClubByUserId(this.getCurrentUser(request).getId());
        int idDTO = clubActivityFormService.commitForm(clubName, getCurrentUser(request).getRealName(), activityName,
                activityPlace, activityTime, activityPeople, isApplyFine, activityInfo, applySelfMoney,
                applyReserveMoney, clubDTO.getId(), fileIdDTO);
        ClubActivityForm clubActivityFormDTO = clubActivityFormService.getFormById(idDTO);
        clubActivityStatusService.saveStatus(clubActivityFormDTO, getCurrentUser(request));
        return "user/index";
    }

    @RequestMapping(value = "/listAllForm")
    public String listAllForm(HttpServletRequest request, HttpServletResponse response, Model model){
        Map mapDTO = clubActivityStatusService.listStatusByFormUserId(getCurrentUser(request).getId());
        model.addAttribute("data",mapDTO);
        return "clubActivity/formList";
    }
}
