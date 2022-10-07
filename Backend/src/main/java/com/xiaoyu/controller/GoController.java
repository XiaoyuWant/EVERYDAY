package com.xiaoyu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoController {

    @RequestMapping("/")
    public String welcome(){
        return "redirect:/login/";
    }
}
