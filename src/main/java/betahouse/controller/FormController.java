package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.core.Base.BaseFile;
import betahouse.model.Club;
import betahouse.model.ClubActivityForm;
import betahouse.model.FormManager;
import betahouse.service.club.ClubActivityFormService;
import betahouse.service.club.ClubActivityStatusService;
import betahouse.service.club.ClubService;
import betahouse.service.club.FormManagerService;
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
import java.util.function.BiConsumer;

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

    @Autowired
    private FormManagerService formManagerService;

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
        int idDTO = clubActivityFormService.commitForm(clubName, activityName,
                activityPlace, activityTime, activityPeople, isApplyFine, activityInfo, applySelfMoney,
                applyReserveMoney,fileIdDTO, getCurrentUser(request));
        ClubActivityForm clubActivityFormDTO = clubActivityFormService.getFormById(idDTO);
        clubActivityStatusService.commitFormStatus(clubActivityFormDTO, getCurrentUser(request));
        return "user/index";
    }

    @RequestMapping(value = "/listAllForm")
    public String listAllForm(HttpServletRequest request, HttpServletResponse response, Model model){
        int idDTO = getCurrentUser(request).getId();
        FormManager formManagerDTO = formManagerService.getFormManagerByApprover(idDTO);
        if(formManagerDTO.getApproverLv() == 1){
            Map mapDTO = clubActivityStatusService.listStatusByFormUserId(idDTO);
            model.addAttribute("data",mapDTO);
            return "clubActivity/formList";
        }
        Map mapDTO = clubActivityStatusService.listStatusByTypeAndLv(formManagerDTO.getFormType(), formManagerDTO.getApproverLv());
        model.addAttribute("data",mapDTO);
        return "clubActivity/formList";
    }

    @RequestMapping(value = "/getFormById")
    public String getFormById(HttpServletRequest request, HttpServletResponse response, Model model,
                              @RequestParam int id){
        ClubActivityForm clubActivityFormDTO = clubActivityFormService.getFormById(id);
        model.addAttribute("clubActivityForm",clubActivityFormDTO);
        return "clubActivity/clubActivityForm";
    }
}
