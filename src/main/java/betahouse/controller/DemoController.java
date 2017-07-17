package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.core.Base.BaseFile;
import betahouse.service.form.FormManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yxm on 2017/7/3.
 */
@Controller
public class DemoController extends BaseController {

    @Autowired
    FormManagerService formManagerService;

    @RequestMapping("/json")
    public String json(HttpServletRequest request, HttpServletResponse response, Model model){
        return ajaxReturn(response,super.demo(),"ha",0);
    }
    @RequestMapping("/hi")
    public String hi(HttpServletRequest request, HttpServletResponse response, Model model,int t){
//        BaseFile baseFile = new BaseFile();
//        int t = baseFile.download(response,"千叶思辩社","test01");
        return ajaxReturn(response,t);
    }
    @RequestMapping("/download")
    public void up(HttpServletRequest request, HttpServletResponse response, Model model){
//        BaseFile baseFile = new BaseFile();
//        int t = baseFile.download(response,"千叶思辩社","test01");
        BaseFile baseFile = new BaseFile();
        int t = baseFile.download(response,"千叶思辩社","xtest01.sql");
        System.out.println(t);
        if(t==1)this.hi(request,response,model,t);
        //return "/demo/hello";
    }
}
