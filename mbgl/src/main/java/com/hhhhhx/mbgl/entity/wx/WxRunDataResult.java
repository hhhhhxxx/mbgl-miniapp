package com.hhhhhx.mbgl.entity.wx;

import lombok.Data;

import java.util.List;

@Data
public class WxRunDataResult {
    private List<stepItem> stepInfoList;
    private Watermark watermark;

    @Data
    public static class stepItem {
        private String timestamp;
        private Integer step;
    }

    @Data
    public static class Watermark {
        private String timestamp;
        private String appid;
    }
}



