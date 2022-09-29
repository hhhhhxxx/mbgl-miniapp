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
@TableName("t_message")
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer type;
    /**
     * 内容
     */
    private String content;

    private LocalDateTime createTime;

    /**
     * 发送者用户ID
     */
    private Integer sendUserId;

    private Integer receiveUserId;
}
