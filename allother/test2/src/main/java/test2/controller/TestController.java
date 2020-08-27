package test2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/sgtest")
@CrossOrigin
public class TestController {

    static {
        System.out.println("我sb了" +
                "");
    }

    @GetMapping("/receivce")
    public String test(HttpServletRequest httpServletRequest, Integer size) {
        System.out.println("----------------------------------------------------------");
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + ":" + cookie.getValue());
            }
        }
        return "test2";
    }

    @PostConstruct
    public void modify() {
        System.out.println("------------TestController");
    }

}
