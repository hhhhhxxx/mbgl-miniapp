package com.hhhhhx.mbgl.service;

import com.hhhhhx.mbgl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.UserLoginVM;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
public interface IUserService extends IService<User> {
    User login(UserLoginVM model);

    User getUserByUsername(String username);
}
