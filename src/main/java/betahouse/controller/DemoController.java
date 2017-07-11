package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.service.club.FormManagerService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String hi(HttpServletRequest request, HttpServletResponse response, Model model){
        logger.warn("你妹啊");
        return "user/userInfo";
    }

}
