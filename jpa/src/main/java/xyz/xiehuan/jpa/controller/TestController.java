package xyz.xiehuan.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/11/16
 * Time: 15:40
 * Describe:
 */
@Controller
public class TestController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(HttpServletResponse response){
        Cookie cookie = new Cookie("1","1");
        //cookie.setDomain("/");
        cookie.setPath("/");
        response.addCookie(cookie);
        return "1212";
    }
}

