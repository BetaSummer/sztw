package betahouse.controller;

import betahouse.controller.Base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by x1654 on 2017/7/14.
 */
@Controller
@RequestMapping(value = "/finance")
public class FinanceController extends BaseController{

    @RequestMapping(value = "/listAllFinancialFlow")
    public String listAllFinancialFlow(HttpServletRequest request, HttpServletResponse response, Model model){
        return "finance/finance";
    }
}
