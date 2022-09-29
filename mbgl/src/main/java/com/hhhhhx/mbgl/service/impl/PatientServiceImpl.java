package com.hhhhhx.mbgl.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Doctor;
import com.hhhhhx.mbgl.entity.Patient;
import com.hhhhhx.mbgl.mapper.PatientMapper;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;
import com.hhhhhx.mbgl.service.IPatientService;
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
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements IPatientService {

    @Override
    public Patient getPatientByUserId(Integer userId) {

        Patient one = this.lambdaQuery().eq(Patient::getUserId, userId).one();

        return one;
    }

    @Override
    public boolean updateByUserId(Patient model) {

        Patient patient = getPatientByUserId(model.getUserId());

        if(patient == null) {
            return this.save(model);
        }

        model.setId(patient.getId());
        return this.updateById(model);
    }

    @Override
    public Page pagePatientOfDoctor(PatientPageVM model) {

        Page<Patient> page = new Page<>(model.getPageIndex(), model.getPageSize());

        page = (Page<Patient>) this.baseMapper.getPatientOfDoctor(page, model.getDoctorUserId(), model.getKey());

        return page;

    }

    @Override
    public Page pagePatient(PatientPageVM model) {
        Page<Patient> page = new Page<>(model.getPageIndex(), model.getPageSize());

        LambdaQueryWrapper<Patient> query = new LambdaQueryWrapper<>();

        // 日后补充模糊查询
        query.like(StrUtil.isNotBlank(model.getKey()),Patient::getName,model.getKey());

        return this.page(page, query);
    }
}
