package com.xiaoyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyu.dao.CommentDao;
import com.xiaoyu.entity.Comment;
import com.xiaoyu.entity.Item;
import com.xiaoyu.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {

    @Resource
    CommentDao commentDao;

    @Override
    public List<Comment> getCommentsByItemId(int itemId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getItemId,itemId);
        List<Comment> comments = commentDao.selectList(queryWrapper);
        return comments;
    }
}

