package com.xiaoyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyu.entity.Comment;
import com.xiaoyu.entity.Item;

import java.util.List;

/**
 * (Comment)表服务接口
 *
 * @author makejava
 * @since 2022-10-08 13:41:27
 */
public interface CommentService extends IService<Comment> {

    public List<Comment> getCommentsByItemId(int itemId);

}

