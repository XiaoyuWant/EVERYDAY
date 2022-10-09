package com.xiaoyu.controller;


import com.fasterxml.jackson.core.JsonEncoding;
import com.sun.org.glassfish.external.statistics.annotations.Reset;
import com.xiaoyu.dao.CommentDao;
import com.xiaoyu.entity.Comment;
import com.xiaoyu.entity.Item;
import com.xiaoyu.entity.User;
import com.xiaoyu.service.CommentService;
import com.xiaoyu.service.ItemService;
import com.xiaoyu.service.UserService;
import com.xiaoyu.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Resource
    CommentService commentService;

    @Resource
    ItemService itemService;

    @Resource
    UserService userService;

    @GetMapping("/{id}")
    public String getDetailsByItem(@PathVariable("id") int id, HttpSession session, Model model) {
        List<Comment> comments = commentService.getCommentsByItemId(id);
        Item item = itemService.getById(id);
        Comment comment = new Comment();
        model.addAttribute("item",item);
        model.addAttribute("comments",comments);
        model.addAttribute("newComment",comment);
        return "comments";
    }

    @PostMapping("{id}/add")
    public String addNewComment(@PathVariable("id") int id,@ModelAttribute Comment newComment, HttpServletRequest request) {
        String userName = CookieUtil.getFieldFromCookies(request.getCookies(), "user");
        User user = userService.getUserByName(userName);
        if(user==null) {
            return "redirect:/login/login";
        }

        Integer itemId = id;
        if(newComment.getContent().length()==0){
            return "redirect:/comment/"+itemId;
        }
        newComment.setUserName(user.getUserName());
        newComment.setItemId(itemId);
        Item item = itemService.getById(itemId);
        item.setCommentCount(item.getCommentCount()+1);
        itemService.updateById(item);
        commentService.save(newComment);
        return "redirect:/comment/" + itemId;
    }



}
