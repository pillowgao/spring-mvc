package test.SpringMVC;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mvc")
public class MvcController {
	@RequestMapping("/hello")
    public String hello(){        
        return "hello";
    }
    @RequestMapping("/person1")
    public String toPerson(Person p){
        System.out.println(p.getName()+" "+p.getAge());
        return "hello";
    }
    //the parameter was converted in initBinder
    @RequestMapping("/date")
    public String date(Date date){
        System.out.println(date);
        return "hello";
    }
    //At the time of initialization,convert the type "String" to type "date"
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
                true));
    }
    //向前台传递参数
    @RequestMapping("/show")
    public String showPerson(Map<String,Object> map){
        Person p =new Person();
        map.put("p", p);
        p.setAge(20);
        p.setName("jayjay");
        return "show";
    }
    //使用@RequestParam注解指定参数的name
    @RequestMapping(value="/param")
    public String testRequestParam(@RequestParam(value="id") Integer id,
            @RequestParam(value="name")String name){
        System.out.println(id+" "+name);
        System.out.println("++++++++++++");
        return "/hello";
    }    
}
