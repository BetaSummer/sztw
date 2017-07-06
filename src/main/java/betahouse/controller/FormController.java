package betahouse.controller;

import betahouse.controller.Base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by x1654 on 2017/7/6.
 */
@Controller
@RequestMapping("/applyClubForm")
public class FormController extends BaseController {
@RequestMapping("/applyFormClubActivity")
// TODO: 2017/7/6 修改方法名称 model里加入clubinfo（by chief id获取）
    public String test(){
        return "clubActivity/clubForm";
    }
}
