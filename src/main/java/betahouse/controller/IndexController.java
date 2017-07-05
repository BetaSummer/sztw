package betahouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by x1654 on 2017/7/3.
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/index","/"})
    public String index(HttpServletRequest request, HttpServletResponse response, Model model){
        return "index/login";
    }
}
