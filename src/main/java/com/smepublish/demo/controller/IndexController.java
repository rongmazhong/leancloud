package com.smepublish.demo.controller;

import org.apache.catalina.manager.util.SessionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 〈首页〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/16
 */
@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping({"/","/index"})
    public String index(){
        return"index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {
        Object exception = request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null) {
            LOGGER.error("异常:{}", exception);
            msg = "登录失败，账号或密码错误!";
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        System.out.println(session);
        subject.logout();
        return "index";
    }
}
