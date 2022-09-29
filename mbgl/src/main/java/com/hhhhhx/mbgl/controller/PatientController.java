package com.hhhhhx.mbgl.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Doctor;
import com.hhhhhx.mbgl.entity.Patient;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.enums.RoleEnum;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;
import com.hhhhhx.mbgl.service.IPatientService;
import com.hhhhhx.mbgl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    IPatientService patientService;

    @Autowired
    IUserService userService;

    @GetMapping("/page/list")
    public RestResponse pageList(PatientPageVM model) {

        Page page = null;

        System.out.println(StrUtil.isNotEmpty(model.getKey()));

        if(model.getDoctorUserId() != null) {

            page = patientService.pagePatientOfDoctor(model);

        } else  {

            page = patientService.pagePatient(model);
        }

        return RestResponse.ok(page);
    }

    @GetMapping("/user/{userId}")
    public RestResponse getById(@PathVariable Integer userId) {

        if(userId == null) {
            return RestResponse.fail();
        }

        User user = userService.getById(userId);

        if(!user.getRoleId().equals(RoleEnum.PATIENT.getCode())) {
            return RestResponse.fail("用户不是患者");
        }
        Patient patient = patientService.getPatientByUserId(userId);

        if(patient == null) {
            return RestResponse.fail("暂无信息");
        }
        return RestResponse.ok(patient);
    }

    @GetMapping("/get/{id}")
    public RestResponse get(@PathVariable Integer id) {

        if(id == null) {
            return RestResponse.fail();
        }

        Patient patient = patientService.getById(id);

        if(patient == null) {
            return RestResponse.fail();
        }

        return RestResponse.ok(patient);

    }

    @PostMapping("/update")
    public RestResponse update(@RequestBody Patient patient) {

        if(patient.getUserId() == null) {
            return RestResponse.fail();
        }

        boolean ok = patientService.updateByUserId(patient);

        if (!ok) {
            return RestResponse.fail();
        }

        return RestResponse.ok();
    }
}
