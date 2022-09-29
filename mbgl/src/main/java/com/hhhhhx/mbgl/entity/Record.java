package com.hhhhhx.mbgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@TableName("t_record")
@Data
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer dataId;

    private Integer value1;

    private Integer value2;

    private LocalDateTime time;

    private Integer patientUserId;
}
