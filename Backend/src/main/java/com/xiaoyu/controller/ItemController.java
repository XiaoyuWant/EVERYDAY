package com.xiaoyu.controller;


import com.xiaoyu.entity.DayItems;
import com.xiaoyu.entity.Item;
import com.xiaoyu.entity.User;
import com.xiaoyu.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Resource
    ItemService itemService;

    @GetMapping("/all")
    @ResponseBody
    public List<Item> getAllItems() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("xiaoyuwang");
        List<Item> allItemsByUser = itemService.getAllItemsByUser(user);
        return allItemsByUser;
    }

    @GetMapping("/byDate")
    public List<DayItems> getDayItemsByUser() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("xiaoyuwang");
        List<DayItems> dayItemsList = itemService.getDayItemsByUser(user);
        return dayItemsList;
    }

    @GetMapping("/byDateHtml")
    public String getDayItemsByUser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user==null) return "redirect:/login/login";

        List<DayItems> dayItemsList = itemService.getDayItemsByUser(user);
        model.addAttribute("dayItems", dayItemsList);
        model.addAttribute("item", new Item());
        model.addAttribute("user",user);
        return "home";
    }

    @PostMapping("/add")
    public String AddRecord(@ModelAttribute Item item, HttpSession session) {
        User user = (User) session.getAttribute("user");
        item.setTime(new Date());
        item.setUser(user.getUserName());
        item.setLikes(0);
        itemService.saveAItem(item);
        return "redirect:/item/byDateHtml";
    }

}
