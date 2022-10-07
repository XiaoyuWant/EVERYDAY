package com.xiaoyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyu.dao.UserDao;
import com.xiaoyu.entity.Item;
import com.xiaoyu.entity.User;
import com.xiaoyu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 11:43:22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public User getUserByName(String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUserName,name);
        User user = userDao.selectOne(queryWrapper);
        return user;
    }

    @Override
    public User registerByNameAndPassword(User user) {
        if( user.getUserName()==null || user.getUserPassword()==null
            || getUserByName(user.getUserName())!=null ) {
            return null;
        }
        userDao.insert(user);
        return user;
    }
}

