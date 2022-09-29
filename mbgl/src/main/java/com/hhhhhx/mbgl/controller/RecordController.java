package com.hhhhhx.mbgl.controller;


import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.RecordAddVM;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@RestController
@RequestMapping("/record")
public class RecordController {



    @PostMapping("/add")
    public RestResponse add(RecordAddVM model) {



        return RestResponse.ok();
    }


}
