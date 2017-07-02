package betahouse._demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Yxm on 2017/7/1.
 */
@Controller
@RequestMapping(value = "/demo")
public class _View {
    @RequestMapping(value = "/view")
    public String test(Model model){
        person p = new person();
        p.setName("aaa");
        p.setAge(18);
        model.addAttribute("word", "hello world");
        model.addAttribute("person", p);
        return "hello";
    }
}
class person{
    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
