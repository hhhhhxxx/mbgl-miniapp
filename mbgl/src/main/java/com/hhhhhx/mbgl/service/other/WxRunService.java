package com.hhhhhx.mbgl.service.other;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hhhhhx.mbgl.config.SystemConfig;
import com.hhhhhx.mbgl.entity.wx.WxJscode2sessionResult;
import com.hhhhhx.mbgl.param.wx.WxDecodeDataVM;
import com.hhhhhx.mbgl.utils.HttpUtil;
import com.hhhhhx.mbgl.utils.RedisConstant;
import com.hhhhhx.mbgl.utils.WxDecodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class WxRunService {

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean sessionkey(Integer userId, String code) {

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code";

        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", systemConfig.getWx().getAppid());
        params.put("secret", systemConfig.getWx().getSecret());
        params.put("js_code", code);


        ResponseEntity<String> response = HttpUtil.get(url, params);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return false;
        }

        String body = response.getBody();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        WxJscode2sessionResult result = JSONUtil.toBean(jsonObject, WxJscode2sessionResult.class);

        stringRedisTemplate.opsForValue()
                .set(RedisConstant.USER_PREFIX + userId, JSONUtil.toJsonStr(result), 3L,TimeUnit.DAYS);

        return true;
    }

    public String decode(Integer userId, WxDecodeDataVM model) {

        String s = stringRedisTemplate.opsForValue()
                .get(RedisConstant.USER_PREFIX + userId);

        System.out.println("微信用户信息"+s);

        if(StrUtil.isEmpty(s)) {
            return null;
        }

        JSONObject jsonObject = JSONUtil.parseObj(s);

        WxJscode2sessionResult result = JSONUtil.toBean(jsonObject, WxJscode2sessionResult.class);

        String session_key = result.getSession_key();

        String data = WxDecodeUtil.decryptWxDataOfHutool(session_key, model.getEncryptedData(), model.getIv());

        return data;
    }
}
