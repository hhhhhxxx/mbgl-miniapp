package com.hhhhhx.mbgl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Doctor;
import com.hhhhhx.mbgl.entity.Patient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@Repository
public interface PatientMapper extends BaseMapper<Patient> {


    IPage<Patient> getPatientOfDoctor(Page<Patient> page, @Param("doctor_user_id") Integer patientUserId, @Param("key") String key);
}
