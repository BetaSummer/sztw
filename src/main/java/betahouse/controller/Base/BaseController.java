package betahouse.controller.Base;
import betahouse.model.UserInfo;
import betahouse.service.user.PowerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static betahouse.core.constant.UserConstant.SESSION_CURRENT_USER;
import static betahouse.core.constant.UserConstant.SESSION_USER_POWER;

/**
 * Created by Yxm on 2017/7/3.
 */
public class BaseController extends betahouse.core.Base.BaseController {

    @Autowired
    private PowerService powerService;

    public String demo (){
        return SESSION_CURRENT_USER;
    }

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
}
