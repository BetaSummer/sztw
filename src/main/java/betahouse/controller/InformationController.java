package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.core.Base.BaseFile;
import betahouse.model.VO.PictureVO;
import betahouse.service.information.AnnouncementService;
import betahouse.service.information.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

import static betahouse.core.constant.FolderNameConstant.RESOURCES;
import static betahouse.core.constant.InformationConstant.PUBLISH_SUCCESS;
import static betahouse.core.constant.InformationConstant.SAVE_SUCCESS;

/**
 * Created by x1654 on 2017/7/7.
 */
@Controller
@RequestMapping(value = "/information")
public class InformationController extends BaseController{

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/doMessage")
    public String doMessage(HttpServletRequest request, HttpServletResponse response, Model model){
        return "manage/doMessage";
    }

    @RequestMapping(value = "/publishAnnouncement", method = RequestMethod.POST)
    public String publishAnnouncement(HttpServletRequest request, HttpServletResponse response, Model model,
                                      @RequestParam String title, @RequestParam String comment){
        announcementService.sendAnnouncement(getCurrentUser(request).getId(), title, comment, 0);
        return ajaxReturn(response, null, PUBLISH_SUCCESS, 0);
    }

    @RequestMapping(value = "/saveAnnouncement", method = RequestMethod.POST)
    public String saveAnnouncement(HttpServletRequest request, HttpServletResponse response, Model model,
                                   @RequestParam int id, @RequestParam String title, @RequestParam String comment){
        announcementService.saveAnnouncement(id, getCurrentUser(request).getId(), title, comment, 0);
        return ajaxReturn(response, null, SAVE_SUCCESS, 0);
    }

    @RequestMapping(value = "/listUnpublishedAnnouncement")
    public String listUnpublishedAnnouncement(HttpServletRequest request, HttpServletResponse response, Model model){
        return ajaxReturn(response, announcementService.listUnpublishedAnnouncement(), "", 0);
    }

    @RequestMapping(value = "/getAnnouncementById")
    public String getAnnouncementById(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam int id){
        model.addAttribute("announcement", announcementService.getAnnouncementById(id));
        return "index/information";
    }

    @RequestMapping(value = "/publishMessage")
    public String publishMessage(HttpServletRequest request, HttpServletResponse response, Model model,
                                 @RequestParam String title, @RequestParam String comment, @RequestParam String toId){
        messageService.sendMessage(getCurrentUser(request).getId(), title, comment, toId, 0);
        return ajaxReturn(response, null, "", 0);
    }

    @RequestMapping(value = "/saveMessage")
    public String saveMessage(HttpServletRequest request, HttpServletResponse response, Model model,
                              @RequestParam String id, @RequestParam String title, @RequestParam String comment,
                              @RequestParam String toId){
        messageService.saveMessage(Integer.parseInt(id), getCurrentUser(request).getId(), title, comment, toId, 0);
        return ajaxReturn(response, null, "", 0);
    }

    @RequestMapping(value = "/uploadFile")
    public String uploadFile(HttpServletRequest request, HttpServletResponse response, Model model,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("picture") MultipartFile file){
        BaseFile baseFileDTO = new BaseFile();
        baseFileDTO.upload(file, RESOURCES+ File.separator+"Img");
        String str = "http://localhost:8080/resources/"+file.getOriginalFilename();
        return ajaxReturn(response, str, "", 0);
    }
}

