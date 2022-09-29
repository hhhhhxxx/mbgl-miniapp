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
 * @since 2022-09-22
 */
@TableName("t_notice")
@Data
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 消息类型
     */
    private Integer type;

    /**
     * 内容
     */
    private String content;

    /**
     * 接收者的反馈
     */
    private Integer op;

    /**
     * 1已读 0未读
     */
    private Integer state;

    /**
     * 附件
     */
    private String attachment;

    /**
     * 接受者用户ID
     */
    private Integer receiveUserId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
