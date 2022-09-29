package com.hhhhhx.mbgl.utils;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.util.Arrays;

public class WxDecodeUtil {

    /**
     * 解密微信加密数据，对称解密使用的算法为 AES-128-CBC，数据采用PKCS#7填充。
     *
     * @param encryptedData 加密串
     * @param sessionKey    会话密钥
     * @param iv            解密算法初始向量
     * @return 解密后的数据
     */
    public static String decryptWxDataOfHutool(String sessionKey,String encryptedData, String iv) {

        try{
            AES aes = new AES(Mode.CBC, Padding.NoPadding, Base64.decodeBase64(sessionKey),
                    Base64.decodeBase64(iv));

            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData));

            if (null != resultByte && resultByte.length > 0) {
                // 删除解密后明文的补位字符
                int padNum = resultByte[resultByte.length - 1];
                if (padNum < 1 || padNum > 32) {
                    padNum = 0;
                }
                resultByte = Arrays.copyOfRange(resultByte, 0, resultByte.length - padNum);
                String result = new String(resultByte, StandardCharsets.UTF_8);
                System.out.println(">>>>> 微信加密数据解析结果: "+result);
                return result;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
