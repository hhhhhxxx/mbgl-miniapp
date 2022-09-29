package com.hhhhhx.mbgl.service;

import com.hhhhhx.mbgl.entity.Connect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.connect.ConnectApplyVM;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhx
 * @since 2022-09-22
 */
public interface IConnectService extends IService<Connect> {

    boolean apply(ConnectApplyVM model);

    boolean confirm(Connect connect);

    boolean refuse(Connect connect);

    boolean disconnect(ConnectApplyVM model);

    Connect getConnectByDoubleId(Integer patientUserId, Integer doctorUserId);
}
