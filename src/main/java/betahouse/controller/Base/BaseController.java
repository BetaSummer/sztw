package betahouse.controller.Base;
import betahouse.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static betahouse.core.constant.UserConstant.SESSION_CURRENT_USER;
/**
 * Created by Yxm on 2017/7/3.
 */
public class BaseController extends betahouse.core.Base.BaseController {
    public String demo (){
        return SESSION_CURRENT_USER;
    }

    protected HttpSession getHttpSession(HttpServletRequest request) {
        return request.getSession();
    }

    protected UserInfo getCurrentUser(HttpServletRequest request) {
        UserInfo cu = (UserInfo) getHttpSession(request).getAttribute(SESSION_CURRENT_USER);
        return cu;
    }
}
