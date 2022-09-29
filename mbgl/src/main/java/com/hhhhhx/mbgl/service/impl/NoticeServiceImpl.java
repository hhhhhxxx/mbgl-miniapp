package com.hhhhhx.mbgl.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Connect;
import com.hhhhhx.mbgl.entity.Doctor;
import com.hhhhhx.mbgl.entity.Notice;
import com.hhhhhx.mbgl.entity.Patient;
import com.hhhhhx.mbgl.entity.enums.ConnectState;
import com.hhhhhx.mbgl.entity.enums.NoticeOption;
import com.hhhhhx.mbgl.entity.enums.NoticeState;
import com.hhhhhx.mbgl.entity.enums.NoticeType;
import com.hhhhhx.mbgl.entity.factory.ConnectNoticeFactory;
import com.hhhhhx.mbgl.mapper.NoticeMapper;
import com.hhhhhx.mbgl.param.notice.NoticeOptionVM;
import com.hhhhhx.mbgl.param.notice.NoticePageVM;
import com.hhhhhx.mbgl.service.IConnectService;
import com.hhhhhx.mbgl.service.IDoctorService;
import com.hhhhhx.mbgl.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.service.IPatientService;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hhx
 * @since 2022-09-22
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Autowired
    IDoctorService doctorService;

    @Autowired
    IPatientService patientService;

    @Autowired
    IConnectService connectService;

    @Override
    public boolean sendApplyConnect(Integer doctorUserId, Patient patient) {

        Notice applyNotice = ConnectNoticeFactory.getApplyNotice(doctorUserId, patient);

        return this.save(applyNotice);
    }

    @Override
    public Page<Notice> pageList(NoticePageVM model) {

        Page<Notice> page = new Page<>(model.getPageIndex(), model.getPageSize());

        this.lambdaQuery()
                .eq(model.getUserId() != null, Notice::getReceiveUserId, model.getUserId())
                .eq(model.getState() != null, Notice::getState, model.getState())
                .page(page);

        return page;
    }

    @Override
    public boolean option(NoticeOptionVM model) {

        Integer id = model.getId();
        Integer option = model.getOption();

        Notice notice = this.getById(id);


        // 同意申请
        if (notice.getType().equals(NoticeType.APPLY_CONNECT.getCode())) {
            return this.applyOption(notice,option);
        }

        return false;
    }

    @Override
    public boolean deleteNotice(Integer id) {


        Notice notice = this.getById(id);

        // 删除建立关系
        if(notice.getType().equals(NoticeType.APPLY_CONNECT.getCode())
                && notice.getOp().equals(NoticeOption.INIT.getCode())) {

            Integer patientUserId = Integer.parseInt(notice.getAttachment());
            Integer doctorUserId = notice.getReceiveUserId();


            Connect connect = connectService.getConnectByDoubleId(patientUserId, doctorUserId);

            if(connect.getState().equals(ConnectState.APPLY.getCode())) {

                this.applyOption(notice,NoticeOption.REFUSED.getCode());
            }

        }

        return this.removeById(id);
    }


    public boolean applyOption(Notice notice,Integer option) {

        if(option.equals(NoticeOption.CONFIRM.getCode())) {
            // 旧通知设置为操作过
            notice.setOp(option);
            notice.setType(NoticeType.INFO.getCode());

            Integer patientUserId = Integer.parseInt(notice.getAttachment());
            Integer doctorUserId = notice.getReceiveUserId();

            // 修改connect
            Connect connect = new Connect();
            connect.setPatientUserId(patientUserId);
            connect.setDoctorUserId(doctorUserId);
            connectService.confirm(connect);


            // 发送同意的通知
            Doctor doctor = doctorService.getDoctorByUserId(doctorUserId);

            Notice refuseNotice = ConnectNoticeFactory.getConfirmNotice(patientUserId, doctor);

            this.save(refuseNotice);

            return this.updateById(notice);
        } else if(option.equals(NoticeOption.REFUSED.getCode())) {

            notice.setType(NoticeType.INFO.getCode());
            notice.setOp(option);

            Integer patientUserId = Integer.parseInt(notice.getAttachment());
            Integer doctorUserId = notice.getReceiveUserId();


            Connect connect = new Connect();
            connect.setPatientUserId(patientUserId);
            connect.setDoctorUserId(doctorUserId);
            connectService.refuse(connect);

            // 发送同意的通知
            Doctor doctor = doctorService.getDoctorByUserId(doctorUserId);

            Notice refuseNotice = ConnectNoticeFactory.getRefuseNotice(patientUserId, doctor);

            this.save(refuseNotice);


            return this.updateById(notice);
        }


        return false;
    }
}
