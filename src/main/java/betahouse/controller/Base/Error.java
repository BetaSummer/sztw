package betahouse.controller.Base;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static betahouse.core.constant.ErrorConstant.ERROR_404;
import static betahouse.core.constant.ErrorConstant.ERROR_500;
import static betahouse.core.constant.ErrorConstant.ERROR_DEFAULT;

/**
 * Created by Yxm on 2017/8/15.
 */
@Controller
public class Error extends BaseController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public String handleError(HttpServletRequest request, HttpServletResponse response, Model model){
        String errorInfo[];
        switch (response.getStatus()){
            case 404:
                errorInfo = ERROR_404;
                break;
            case 500:
                errorInfo = ERROR_500;
                break;
            default:
                errorInfo = ERROR_DEFAULT;
        }
        model.addAttribute("status",response.getStatus());
        model.addAttribute("errorInfo",errorInfo);
        return "index/error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}