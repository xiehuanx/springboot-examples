package com.example.demo.controller;

import com.example.demo.entity.TreeNode;
import com.example.demo.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Author: xie
 * Email: 1487471733@qq.com
 * Date: 2017/9/22
 * Time: 13:46
 * Describe:
 */
@Controller
public class indexController extends BaseController{

    private static final transient org.slf4j.Logger log = LoggerFactory.getLogger(indexController.class);

    @Autowired
    private PermissionService permissionService;

    @GetMapping(value = "/admin/index")
    public String index(Model model) {
        List<TreeNode> treeNodes = permissionService.listTree(getCurrentLoginId());
        model.addAttribute("treeNodes", treeNodes);
        return "/admin/index";
    }

    /**
     * 登陆页面
     * @return
     */
    @GetMapping("/admin/login")
    public String loginForm() {
        return "/admin/login";
    }

    @GetMapping("/admin/welcome")
    public String welcome() {
        return "/admin/welcome";
    }

    @RequestMapping("/admin/mylogin")
    public String mylogin(String username, String password, Map<String, Object> map) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            String simpleName = e.getClass().getSimpleName();
            if ("UnknownAccountException".equals(simpleName)) {
                map.put("msg", "用户名或密码不正确");
                return "/admin/login";
            } else if("IncorrectCredentialsException".equals(simpleName)){
                map.put("msg", "用户名或密码不正确");
                return "/admin/login";
            }
        }
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
            return "redirect:/admin/index.html";
        }
        return "redirect:/admin/login.html";
    }
    @RequestMapping("/admin/logout")
    public  String mylogout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            if (log.isDebugEnabled()) {
                log.debug("用户" + subject.getPrincipals() + "退出登录");
            }

        }
        return "redirect:/admin/login.html";
    }
}
