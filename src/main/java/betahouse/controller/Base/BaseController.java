package betahouse.controller.Base;
import static betahouse.core.constant.UserConstant.SESSION_CURRENT_USER;
/**
 * Created by Yxm on 2017/7/3.
 */
public class BaseController extends betahouse.core.Base.BaseController {
    public String demo (){
        return SESSION_CURRENT_USER;
    }
}
