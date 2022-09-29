package com.hhhhhx.mbgl.controller;


import cn.hutool.core.bean.BeanUtil;
import com.hhhhhx.mbgl.dto.UserDTO;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.UserLoginVM;
import com.hhhhhx.mbgl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public RestResponse login(@RequestBody UserLoginVM model) {

        User login = userService.login(model);

        if (login == null) {
            return RestResponse.fail();
        }

        UserDTO userDTO = BeanUtil.toBean(login, UserDTO.class);
        return RestResponse.ok(userDTO);
    }
}
