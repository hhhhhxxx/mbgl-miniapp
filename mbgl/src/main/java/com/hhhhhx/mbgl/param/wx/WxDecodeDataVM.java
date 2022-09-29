package com.hhhhhx.mbgl.param.wx;

import lombok.Data;

@Data
public class WxDecodeDataVM {
    private String encryptedData;
    private String iv;
}
