package cn.pzhu.springbootsecurity.controller;

import cn.pzhu.springbootsecurity.entity.Role;
import cn.pzhu.springbootsecurity.entity.User;
import cn.pzhu.springbootsecurity.service.AmdinService;
import cn.pzhu.springbootsecurity.service.StudentService;
import cn.pzhu.springbootsecurity.service.TeacherService;
import cn.pzhu.springbootsecurity.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static cn.pzhu.springbootsecurity.util.common.Content.AUTHORITIE;
import static cn.pzhu.springbootsecurity.util.common.Content.USER_SESSION;

/**
 * User controller
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/redirect")
    public String login(HttpSession session){
        //获取用户登录所有信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       /* System.out.println("getPrincipal"+authentication.getPrincipal());
        System.out.println("getDetails"+authentication.getDetails());
        System.out.println("getCredentials"+authentication.getCredentials());*/

        //获取权限
        //Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        User user = (User)authentication.getPrincipal();
        //获取username
        String username =user.getUsername();
        String author = user.getRoles().get(0).getName();
        Integer uid = userService.getRoleId(username,author);
        //使用迭代器获取用户权限
        //Iterator iterator = authorities.iterator();
        //String role = String.valueOf(iterator.next());
        session.setAttribute(USER_SESSION,user);
        session.setAttribute(AUTHORITIE,author);
        session.setAttribute("uid",uid);
        return "redirect:/"+author+"/main.html";
    }
    @GetMapping("/changPwd/{username}")
    public String changePwdpag(@PathVariable("username") String username,Model model){
        model.addAttribute("username",username);
        return "changePwd";
    }
    @PutMapping("/changePwd")
    public String changePwd(String username,
                            String password,
                            String newpassword,
                            Model model,
                            HttpSession session){
        boolean flag = userService.changeUserPassword(username, password, newpassword);
        if (!flag){
            model.addAttribute("msg","密码错误");
            return "changePwd";
        }
        return "redirect:/logout";
    }
}
