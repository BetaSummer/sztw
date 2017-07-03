package betahouse.core.Base;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yxm on 2017/7/3.
 */
public class BaseController {
    public BaseController(){

    }
    protected static String ajaxReturn(HttpServletResponse response, Object data) {
        render(response, JSON.toJSONString(data));
        return null;
    }

    protected static String ajaxReturn(HttpServletResponse response, Object data, String message, int status) {
        Map<String, Object> jsonData = new HashMap();
        jsonData.put("data", data);
        jsonData.put("message", message);
        jsonData.put("status", status);
        render(response, JSON.toJSONString(jsonData));
        return null;
    }

    private static void render(HttpServletResponse response, String text) {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        PrintWriter pw = null;

        try{
            pw = response.getWriter();
            pw.write(text);
        } catch (IOException var8) {
            var8.printStackTrace();
        } finally {
            pw.close();
        }

    }
}
