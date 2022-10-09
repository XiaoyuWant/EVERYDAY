package com.xiaoyu.controller;


import com.xiaoyu.entity.User;
import com.xiaoyu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static com.xiaoyu.util.CookieUtil.addCookieForHttpResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    UserService userService;

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping(value = "/tryLogin",params = "action=login")
    public String tryLogin(@ModelAttribute User user, HttpServletResponse response) {
        String name = user.getUserName();
        User userOfDatabase = userService.getUserByName(name);
        if(userOfDatabase!=null && user.getUserPassword().equals(userOfDatabase.getUserPassword())) {
            addCookieForHttpResponse(response, userOfDatabase.getUserName());
            return "redirect:/item/byDateHtml";
        } else {
            return "login";
        }
    }

    @PostMapping(value = "/tryLogin",params = "action=register")
    public String tryRegister(@ModelAttribute User user, HttpServletResponse response) {
        User userOfDatabase = userService.registerByNameAndPassword(user);
        if(userOfDatabase!=null){
            addCookieForHttpResponse(response, userOfDatabase.getUserName());
            return "redirect:/item/byDateHtml";
        } else {
            return "redirect:/login/login";
        }
    }




}
