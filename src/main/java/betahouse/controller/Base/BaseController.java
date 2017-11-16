package betahouse.controller.Base;
import betahouse.model.UserInfo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static betahouse.core.constant.UserConstant.SESSION_CURRENT_USER;
import static betahouse.core.constant.UserConstant.SESSION_USER_POWER;

/**
 * Created by Yxm on 2017/7/3.
 */
public class BaseController extends betahouse.core.Base.BaseController {

    protected HttpSession getHttpSession(HttpServletRequest request) {
        return request.getSession();
    }

    protected UserInfo getCurrentUser(HttpServletRequest request) {
        UserInfo userInfoDTO = (UserInfo) getHttpSession(request).getAttribute(SESSION_CURRENT_USER);
        return userInfoDTO;
    }
  
    protected List<Integer> getLicence(HttpServletRequest request) {
        List<Integer> licence = (List<Integer>) getHttpSession(request).getAttribute(SESSION_USER_POWER);
        return licence;
    }

    protected String error(HttpServletRequest request, HttpServletResponse response, Model model, int status){
        return ajaxReturn(response, status);
    }
}
