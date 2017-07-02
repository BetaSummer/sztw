package betahouse._demo;

import betahouse.mapper.UserMapper;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yxm on 2017/7/1.
 */
@RestController
@RequestMapping(value = "/demo")
public class _API {
    @Autowired
    UserMapper map;
////    @Autowired
////    PeopleMapper map;
////    //返回字符串形式
////    @RequestMapping(value = "/hello")
////    public String hello(){
////        return "hello world";
////    }
////    //通过FastJson返回json对象
    @RequestMapping(value = "db")
    public String db(){
        return JSON.toJSONString(map.selectAll());
    }
}
