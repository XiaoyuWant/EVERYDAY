package com.xiaoyu.controller;


import com.xiaoyu.entity.Item;
import com.xiaoyu.entity.User;
import com.xiaoyu.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ItemController {
    @Resource
    ItemService itemService;

    @RequestMapping("/all")
    @ResponseBody
    public List<Item> getAllItems(){
        User user = new User("xiaoyuwang",1);
        List<Item> allItemsByUser = itemService.getAllItemsByUser(user);
        return allItemsByUser;
    }

}
