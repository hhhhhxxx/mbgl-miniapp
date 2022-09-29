package com.hhhhhx.mbgl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Doctor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
public interface IDoctorService extends IService<Doctor> {


    Doctor getDoctorByUserId(Integer userId);

    boolean updateByUserId(Doctor doctor);

    Page pageDoctorOfPatient(DoctorPageVM model);

    Page pageDoctor(DoctorPageVM model);
}
