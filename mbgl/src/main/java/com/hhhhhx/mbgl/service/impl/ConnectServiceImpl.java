package com.hhhhhx.mbgl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.hhhhhx.mbgl.entity.Connect;
import com.hhhhhx.mbgl.entity.Doctor;
import com.hhhhhx.mbgl.entity.Message;
import com.hhhhhx.mbgl.entity.Patient;
import com.hhhhhx.mbgl.entity.enums.ConnectState;
import com.hhhhhx.mbgl.mapper.ConnectMapper;
import com.hhhhhx.mbgl.param.connect.ConnectApplyVM;
import com.hhhhhx.mbgl.service.IConnectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.service.IDoctorService;
import com.hhhhhx.mbgl.service.INoticeService;
import com.hhhhhx.mbgl.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hhx
 * @since 2022-09-22
 */
@Service
public class ConnectServiceImpl extends ServiceImpl<ConnectMapper, Connect> implements IConnectService {

    @Autowired
    INoticeService noticeService;
    @Autowired
    IDoctorService doctorService;
    @Autowired
    IPatientService patientService;

    @Override
    public boolean apply(ConnectApplyVM model) {

        Integer patientUserId = model.getPatientUserId();
        Integer doctorId = model.getDoctorId();
        Doctor doctor = doctorService.getById(doctorId);

        if (doctor == null) {
            return false;
        }
        Integer doctorUserId = doctor.getUserId();



        Connect oldConnect = this.getConnectByDoubleId(patientUserId, doctorUserId);

        // 已经有了 就不要重复
        if(oldConnect != null) {
            return false;
        }

        Connect connect = new Connect();
        connect.setPatientUserId(patientUserId);
        connect.setDoctorUserId(doctorUserId);
        connect.setState(ConnectState.APPLY.getCode());

        boolean save = this.save(connect);


        // 发通知
        Patient patient = patientService.getPatientByUserId(patientUserId);

        noticeService.sendApplyConnect(doctorUserId,patient);

        return save;
    }

    @Override
    public boolean confirm(Connect connect) {

        return this.lambdaUpdate()
                .eq(Connect::getPatientUserId, connect.getPatientUserId())
                .eq(Connect::getDoctorUserId, connect.getDoctorUserId())
                .set(Connect::getState, ConnectState.SUCEESS.getCode()).update();
    }

    @Override
    public boolean refuse(Connect connect) {

        LambdaQueryWrapper<Connect> query =  new LambdaQueryWrapper<>();

        query.eq(Connect::getPatientUserId, connect.getPatientUserId())
                .eq(Connect::getDoctorUserId, connect.getDoctorUserId());

        return this.remove(query);
    }

    @Override
    public boolean disconnect(ConnectApplyVM model) {

        Doctor doctor = doctorService.getById(model.getDoctorId());

        Connect connect = new Connect();
        connect.setPatientUserId(model.getPatientUserId());
        connect.setDoctorUserId(doctor.getUserId());

        return this.refuse(connect);
    }

    @Override
    public Connect getConnectByDoubleId(Integer patientUserId, Integer doctorUserId) {

        return  this.lambdaQuery()
                .eq(Connect::getPatientUserId, patientUserId)
                .eq(Connect::getDoctorUserId, doctorUserId).one();

    }


}
