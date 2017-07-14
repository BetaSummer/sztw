package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.core.Base.BaseFile;
import betahouse.model.Club;
import betahouse.model.ClubActivityApprove;
import betahouse.model.ClubActivityForm;
import betahouse.model.FormManager;
import betahouse.service.club.*;
import betahouse.service.file.FileService;
import betahouse.service.user.UserInfoService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static betahouse.core.constant.CommonConstant.noPower;
import static betahouse.core.constant.FormConstant.CLUB_ACTIVITY_NOT_APPROVE;

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

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ClubActivityApproveService clubActivityApproveService;

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
        return "redirect:/index";
    }

    @RequestMapping(value = "/listAllForm")
    public String listAllForm(HttpServletRequest request, HttpServletResponse response, Model model){
        int idDTO = getCurrentUser(request).getId();
        FormManager formManagerDTO = formManagerService.getFormManagerByApprover(idDTO);
        List<Integer> listDTO = JSON.parseArray(formManagerDTO.getApproverForm(), Integer.class);
        int lvDTO = listDTO.get(0);
        if(lvDTO == -1){
            return noPower;
        }
        if(lvDTO == 1){
            Map mapDTO = clubActivityStatusService.listStatusByFormUserId(idDTO);
            model.addAttribute("data",mapDTO);
            return "clubActivity/formList";
        }
        Map mapDTO = clubActivityStatusService.listStatusOverTypeAndLv(1, lvDTO);
        model.addAttribute("data",mapDTO);
        return "clubActivity/formList";
    }

    @RequestMapping(value = "/getFormById")
    public String getFormById(HttpServletRequest request, HttpServletResponse response, Model model,
                              @RequestParam int id){
        ClubActivityForm clubActivityFormDTO = clubActivityFormService.getFormById(id);
        List<ClubActivityApprove> listDTO = clubActivityApproveService.listApproveByFormId(id);
        String[][] approverDTO = new String[4][2];
        approverDTO[0] = new String[]{userInfoService.getUserInfoById(clubService.getClubById(clubActivityFormDTO.getClubId()).getUserId()).getRealName(), ""};
        for(int i = 1; i<approverDTO.length; i++){
            approverDTO[i] = new String[]{CLUB_ACTIVITY_NOT_APPROVE[0], CLUB_ACTIVITY_NOT_APPROVE[1]};
        }
        for(int i = 0; i<listDTO.size(); i++){
            String realNameDTO = userInfoService.getUserInfoById(listDTO.get(i).getApproveUserId()).getRealName();
            approverDTO[i+1] = new String[]{realNameDTO, listDTO.get(i).getComment()};
        }
        Club clubDTO = clubService.getClubById(clubActivityFormDTO.getClubId());
        int[] moneyDTO = new int[]{clubDTO.getSelfMoney(), clubDTO.getReserveMoney()};
        model.addAttribute("clubActivityForm",clubActivityFormDTO);
        model.addAttribute("approver", approverDTO);
        model.addAttribute("money", moneyDTO);
        return "clubActivity/clubActivityForm";
    }
}
