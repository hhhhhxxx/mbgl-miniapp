package com.hhhhhx.mbgl.service.impl;

import com.hhhhhx.mbgl.entity.Message;
import com.hhhhhx.mbgl.mapper.MessageMapper;
import com.hhhhhx.mbgl.service.IMessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
