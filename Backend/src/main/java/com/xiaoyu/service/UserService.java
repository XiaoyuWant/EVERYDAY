package com.xiaoyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyu.entity.User;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-10-07 11:43:22
 */
public interface UserService extends IService<User> {
    public User getUserByName(String name);

    public User registerByNameAndPassword(User user);

}

