package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.core.mail.MailCore;
import betahouse.service.form.FormManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Yxm on 2017/7/3.
 */
@Controller
public class DemoController extends BaseController {

    @Autowired
    FormManagerService formManagerService;

    @Autowired
    MailCore mailCore;

//    @RequestMapping("/json")
//    public String json(HttpServletRequest request, HttpServletResponse response, Model model){
//        return "index/error";
//    }
//    @RequestMapping("/hi")
//    public String hi(HttpServletRequest request, HttpServletResponse response, Model model){
//        return ajaxReturn(response,status);
//    }
//    @RequestMapping("/download")
//    public void up(HttpServletRequest request, HttpServletResponse response, Model model){
//        //return "/demo/hello";
//    }
//    @RequestMapping("/demo")
//    public String demo(HttpServletRequest request, HttpServletResponse response, Model model){
//        String address ;
//        try {
//            address = InetAddress.getLocalHost().getHostAddress();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return ajaxReturn(response,address);
//    }

//    @RequestMapping(value = "/wangEditor")
//    public String wangEditor(HttpServletRequest request, HttpServletResponse response, Model model){
//        return "demo/wangEditor";
//    }

//    @RequestMapping(value = "/test")
//    public String test(HttpServletRequest request, HttpServletResponse response, Model model){
////        String s = "[{'id':1},{'id':1},{'id':1}]";
////        String t = "[{'id':6,'password':'1','tel':'1590000000','eMail':'11111@qq.com'},{'id':5,'password':'1','tel':'22222','eMail':'2222'},{'id':3,'password':'1','tel':'0','eMail':'600000'},{'id':2,'password':'1','tel':'22222','eMail':'22222'},{'id':1,'password':'1','tel':'33333','eMail':'11111'}]";
////        List list = JSON.parseArray(s);
//        List<Integer> list = new ArrayList<>();
//        list.add(0, 1);
//        list.add(1, 3);
//        list.add(2, 2);
//        System.out.println(list.toString());
//        return ajaxReturn(response, list);
//    }

}
