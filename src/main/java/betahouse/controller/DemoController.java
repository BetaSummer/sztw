package betahouse.controller;

import betahouse.controller.Base.BaseController;
import betahouse.model.Test;
import betahouse.model.User;
import betahouse.service.user.AdminServiceImpl;
import betahouse.service.user.AdminService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
    AdminService adminService = new AdminServiceImpl();
    @RequestMapping("/json")
    public String json(HttpServletRequest request, HttpServletResponse response, Model model){
        return ajaxReturn(response,super.demo(),"ha",0);
    }
    @Autowired
    TestMapper testMapper;
    @RequestMapping("/hi")
    public String hi(HttpServletRequest request, HttpServletResponse response, Model model){
//        int arr[][] = new int[10][2];
//        for(int i =0;i<arr.length;i++)
//            arr[i][0] = i;
        List<User> arr = new ArrayList<User>();
        User u  = new User();
        u.setId(1);
        u.setUsername("aaa");
        u.setPassword("111");
        arr.add(u);
        User u1  = new User();
        u1.setId(2);
        u1.setUsername("bbb");
        u1.setPassword("222");
        arr.add(u1);
        Test test = new Test();
        test.setApprove(JSON.toJSONString(arr));
        String s = JSON.toJSONString(arr);
        System.out.println(s);
        List<User> l = JSONArray.parseArray(s,User.class);
        for(User uu : l){
            System.out.println(uu.getId());
            System.out.println(uu.getPassword());
            System.out.println(uu.getUsername());
        }
//        testMapper.insert(test);
//        List<Test>testMapper.selectAll();

        return ajaxReturn(response,arr,"sz",0);
    }

}
