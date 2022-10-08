package com.xiaoyu.controller;


import com.fasterxml.jackson.core.JsonEncoding;
import com.sun.org.glassfish.external.statistics.annotations.Reset;
import com.xiaoyu.dao.CommentDao;
import com.xiaoyu.entity.Comment;
import com.xiaoyu.entity.Item;
import com.xiaoyu.entity.User;
import com.xiaoyu.service.CommentService;
import com.xiaoyu.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Resource
    CommentService commentService;

    @Resource
    ItemService itemService;

    @GetMapping("/{id}")
    public String getDetailsByItem(@PathVariable("id") int id, HttpSession session, Model model) {
        List<Comment> comments = commentService.getCommentsByItemId(id);
        Item item = itemService.getById(id);
        Comment comment = new Comment();
        session.setAttribute("item",item);
        model.addAttribute("comments",comments);
        model.addAttribute("newComment",comment);
        return "comments";

    }

    @PostMapping("/add")
    public String addNewComment(@ModelAttribute Comment newComment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user==null) {
            return "redirect:/login/login";
        }
        Item item = (Item) session.getAttribute("item");
        Integer itemId = item.getItemId();
        if(newComment.getContent().length()==0){
            return "redirect:/comment/"+itemId;
        }
        newComment.setUserName(user.getUserName());
        newComment.setItemId(itemId);
        commentService.save(newComment);
        return "redirect:/comment/"+itemId;
    }



}
