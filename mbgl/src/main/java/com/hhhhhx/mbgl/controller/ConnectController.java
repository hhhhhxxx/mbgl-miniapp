package com.hhhhhx.mbgl.controller;


import com.hhhhhx.mbgl.entity.Connect;
import com.hhhhhx.mbgl.entity.Doctor;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.connect.ConnectApplyVM;
import com.hhhhhx.mbgl.service.IConnectService;
import com.hhhhhx.mbgl.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hhx
 * @since 2022-09-22
 */
@RestController
@RequestMapping("/connect")
public class ConnectController {

    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private IConnectService connectService;

    @PostMapping("/apply")
    public RestResponse apply(@RequestBody ConnectApplyVM model) {

        boolean ok = connectService.apply(model);

        if(!ok) {
            return RestResponse.fail();
        }

        return RestResponse.ok();
    }

    @PostMapping("/disconnect")
        public RestResponse disconnect(@RequestBody ConnectApplyVM model) {


        boolean ok = connectService.disconnect(model);

        return RestResponse.ok();
    }

    @GetMapping("/state")
    public RestResponse state(ConnectApplyVM model) {

        Integer patientUserId = model.getPatientUserId();
        Integer doctorId = model.getDoctorId();
        Doctor doctor = doctorService.getById(doctorId);

        if (doctor == null) {
            return RestResponse.fail();
        }

        Integer doctorUserId = doctor.getUserId();


        Connect oldConnect = connectService.getConnectByDoubleId(patientUserId, doctorUserId);

        if(oldConnect == null) {
            return RestResponse.ok(0);
        }

        return RestResponse.ok(oldConnect.getState());
    }
}
