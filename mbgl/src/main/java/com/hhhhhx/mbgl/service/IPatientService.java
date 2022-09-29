package com.hhhhhx.mbgl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Patient;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
public interface IPatientService extends IService<Patient> {

    Patient getPatientByUserId(Integer userId);

    boolean updateByUserId(Patient patient);

    Page pagePatientOfDoctor(PatientPageVM model);

    Page pagePatient(PatientPageVM model);
}
