package com.hhhhhx.mbgl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Doctor;
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
public interface DoctorMapper extends BaseMapper<Doctor> {

    IPage<Doctor> getDoctorOfPatient(Page<Doctor> page, @Param("patient_user_id") Integer patientUserId, @Param("key") String key);
}
