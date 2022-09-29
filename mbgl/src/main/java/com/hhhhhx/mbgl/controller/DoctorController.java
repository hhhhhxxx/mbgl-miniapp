package com.hhhhhx.mbgl.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Doctor;
import com.hhhhhx.mbgl.entity.Patient;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.enums.RoleEnum;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;
import com.hhhhhx.mbgl.service.IDoctorService;
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
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDoctorService doctorService;

    @GetMapping("/page/list")
    public RestResponse pageList(DoctorPageVM model) {

        Page page = null;

        System.out.println(StrUtil.isNotEmpty(model.getKey()));

        if(model.getPatientUserId() != null) {

            page = doctorService.pageDoctorOfPatient(model);

        } else  {

            page = doctorService.pageDoctor(model);
        }

        return RestResponse.ok(page);
    }




    @GetMapping("/get/{id}")
    public RestResponse get(@PathVariable Integer id) {

        if(id == null) {
            return RestResponse.fail();
        }

        Doctor doctor = doctorService.getById(id);

        if(doctor == null) {
            return RestResponse.fail();
        }

        return RestResponse.ok(doctor);
    }

    @GetMapping("/user/{userId}")
    public RestResponse getById(@PathVariable Integer userId) {

        if(userId == null) {
            return RestResponse.fail();
        }

        User user = userService.getById(userId);

        if(!user.getRoleId().equals(RoleEnum.DOCTOR.getCode())) {
            return RestResponse.fail("用户不是患者");
        }
        Doctor doctor = doctorService.getDoctorByUserId(userId);

        if(doctor == null) {
            return RestResponse.fail("暂无信息");
        }
        return RestResponse.ok(doctor);
    }

    @PostMapping("/update")
    public RestResponse update(@RequestBody Doctor doctor) {

        if(doctor.getUserId() == null) {
            return RestResponse.fail();
        }

        boolean ok = doctorService.updateByUserId(doctor);

        if (!ok) {
            return RestResponse.fail();
        }

        return RestResponse.ok();
    }
}
