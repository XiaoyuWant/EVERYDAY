package com.xiaoyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyu.entity.Item;
import com.xiaoyu.entity.User;

import java.util.List;

/**
 * (TItem)表服务接口
 *
 * @author makejava
 * @since 2022-10-06 16:58:29
 */
public interface ItemService extends IService<Item> {
    public List<Item> getAllItemsByUser(User user);

}

