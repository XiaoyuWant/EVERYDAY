package com.xiaoyu.controller;

import com.xiaoyu.entity.DayItems;
import com.xiaoyu.entity.Item;
import com.xiaoyu.entity.User;
import com.xiaoyu.service.ItemService;
import com.xiaoyu.service.UserService;
import com.xiaoyu.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Resource
    ItemService itemService;
    @Resource
    UserService userService;

    @GetMapping("/byDateHtml")
    public String getDayItemsByUser(Model model, HttpServletRequest request) {
        String userName = CookieUtil.getFieldFromCookies(request.getCookies(), "user");
        User user = userService.getUserByName(userName);
        List<DayItems> dayItemsList = itemService.getPublicDayItems();
        model.addAttribute("dayItems", dayItemsList);
        model.addAttribute("item", new Item());
        model.addAttribute("user",user);
        return "public";
    }


    @PostMapping("/add")
    public String AddRecord(@ModelAttribute Item item, HttpServletRequest request) {
        String userName = CookieUtil.getFieldFromCookies(request.getCookies(), "user");
        User user = userService.getUserByName(userName);
        if(user==null) return "redirect:/login/login";
        item.setTime(new Date());
        item.setUser(user.getUserName());
        item.setLikes(0);
        item.setIsPublic(1);
        item.setCommentCount(0);
        if(item.getContent().length()!=0) {
            itemService.saveAItem(item);
        }
        return "redirect:/public/byDateHtml";
    }


}
