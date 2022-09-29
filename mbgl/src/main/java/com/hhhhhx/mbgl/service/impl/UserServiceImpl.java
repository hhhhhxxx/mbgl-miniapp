package com.hhhhhx.mbgl.service.impl;

import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.mapper.UserMapper;
import com.hhhhhx.mbgl.param.UserLoginVM;
import com.hhhhhx.mbgl.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User login(UserLoginVM model) {
        User queryUser = this.getUserByUsername(model.getUsername());

        if(queryUser != null && queryUser.getPassword().equals(model.getPassword())) {
            return queryUser;
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {

        User user = this.lambdaQuery().eq(User::getUsername, username).one();

        return user;
    }
}
