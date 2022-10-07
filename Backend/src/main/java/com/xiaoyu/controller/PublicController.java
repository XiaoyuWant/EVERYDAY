package com.xiaoyu.controller;

import com.xiaoyu.entity.DayItems;
import com.xiaoyu.entity.Item;
import com.xiaoyu.entity.User;
import com.xiaoyu.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Resource
    ItemService itemService;

    @GetMapping("/byDateHtml")
    public String getDayItemsByUser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<DayItems> dayItemsList = itemService.getPublicDayItems();
        model.addAttribute("dayItems", dayItemsList);
        model.addAttribute("item", new Item());
        model.addAttribute("user",user);
        return "public";
    }


    @PostMapping("/add")
    public String AddRecord(@ModelAttribute Item item, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user==null) return "redirect:/login/login";
        item.setTime(new Date());
        item.setUser(user.getUserName());
        item.setLikes(0);
        item.setIsPublic(1);
        if(item.getContent().length()!=0) {
            itemService.saveAItem(item);
        }
        return "redirect:/public/byDateHtml";
    }


}
