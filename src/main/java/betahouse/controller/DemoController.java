package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.core.Base.BaseFile;
import betahouse.core.office.HSSF;
import betahouse.model.User;
import betahouse.service.form.FormManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping("/demo")
    public void demo(HttpServletRequest request, HttpServletResponse response, Model model){
        HSSF hssf = new HSSF("demo","test");
        hssf.create("zxk dsb"," 名单");


        List<User> list = new ArrayList<>();

        User user = new User();
        user.setId(1);
        user.setUsername("aaa");
        user.setPassword("123");
        list.add(user);

        User user2 = new User();
        user2.setId(1);
        user2.setUsername("aaa");
        user2.setPassword("123");
        list.add(user2);

        String name[] = {"id","用户名","密码"};
        hssf.insert(0,0,0,null,list);
       // hssf.set(0,0,0,"tm");

        BaseFile baseFile = new BaseFile();
        baseFile.download(response,"Office"+ File.separator+"Excel"+File.separator+"demo", "test.xls");

    }

    @RequestMapping(value = "/wangEditor")
    public String wangEditor(HttpServletRequest request, HttpServletResponse response, Model model){
        return "demo/wangEditor";
    }

}
