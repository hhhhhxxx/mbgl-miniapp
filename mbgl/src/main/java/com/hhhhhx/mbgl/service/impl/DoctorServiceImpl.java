package com.hhhhhx.mbgl.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Doctor;
import com.hhhhhx.mbgl.entity.Patient;
import com.hhhhhx.mbgl.mapper.DoctorMapper;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;
import com.hhhhhx.mbgl.service.IDoctorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements IDoctorService {

    @Override
    public Doctor getDoctorByUserId(Integer userId) {

        return this.lambdaQuery().eq(Doctor::getUserId, userId).one();
    }

    @Override
    public boolean updateByUserId(Doctor model) {

        // 加入没有详细资料就插入
        Doctor doctor = getDoctorByUserId(model.getUserId());

        if (doctor == null) {
            return this.save(model);
        }

        model.setId(doctor.getId());
        return this.updateById(model);
    }

    @Override
    public Page pageDoctorOfPatient(DoctorPageVM model) {

        Page<Doctor> page = new Page<>(model.getPageIndex(), model.getPageSize());

        page = (Page<Doctor>) this.baseMapper.getDoctorOfPatient(page, model.getPatientUserId(), model.getKey());

        return page;
    }

    @Override
    public Page pageDoctor(DoctorPageVM model) {
        Page<Doctor> page = new Page<>(model.getPageIndex(), model.getPageSize());


        LambdaQueryWrapper<Doctor> query = new LambdaQueryWrapper<>();

        if(StrUtil.isNotBlank(model.getKey())) {
            // 条件判断写外面 不然报错
            query.and(item ->
                    item.like(Doctor::getName, model.getKey())
                            .or()
                            .like(Doctor::getRoom, model.getKey()));
        }

        return this.page(page, query);
    }
}
