package com.hhhhhx.mbgl.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Notice;
import com.hhhhhx.mbgl.entity.enums.NoticeOption;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.notice.NoticeOptionVM;
import com.hhhhhx.mbgl.param.notice.NoticePageVM;
import com.hhhhhx.mbgl.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hhx
 * @since 2022-09-22
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    INoticeService noticeService;

    @GetMapping("/page/list")
    public RestResponse getNoticeByUserId(NoticePageVM model) {

        Page<Notice> page = noticeService.pageList(model);

        return RestResponse.ok(page);
    }

    @PostMapping("/option")
    public RestResponse option(@RequestBody NoticeOptionVM model) {

        boolean ok = noticeService.option(model);

        if (!ok) {
            return RestResponse.fail();
        }
        return RestResponse.ok();
    }


    @PostMapping("/delete/{id}")
    public RestResponse delete(@PathVariable Integer id) {


        boolean ok = noticeService.deleteNotice(id);

        if (!ok) {
            return RestResponse.fail();
        }
        return RestResponse.ok();
    }
}
