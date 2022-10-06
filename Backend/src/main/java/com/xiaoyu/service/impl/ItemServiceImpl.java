package com.xiaoyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyu.dao.ItemDao;
import com.xiaoyu.entity.Item;
import com.xiaoyu.entity.User;
import com.xiaoyu.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Item)表服务实现类
 *
 * @author makejava
 * @since 2022-10-06 16:58:29
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemDao, Item> implements ItemService {
    @Resource
    ItemDao itemDao;

    @Override
    public List<Item> getAllItemsByUser(User user) {
        LambdaQueryWrapper<Item> queryWrapper = new LambdaQueryWrapper<Item>();
        queryWrapper.like(Item::getUser, user.getName());
        List<Item> items = itemDao.selectList(queryWrapper);
        return items;

    }
}

