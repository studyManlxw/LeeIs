package com.dhl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhl.model.entity.User;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2023-04-24 15:12:44
 */
public interface UserService extends IService<User> {

    User getUserInfoByOpenId(String openId);
}

