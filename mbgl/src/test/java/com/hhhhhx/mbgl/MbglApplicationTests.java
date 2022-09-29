package com.hhhhhx.mbgl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.hhhhhx.mbgl.config.SystemConfig;
import com.hhhhhx.mbgl.entity.wx.WxJscode2sessionResult;
import com.hhhhhx.mbgl.utils.WxDecodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = MbglApplication.class)
@RunWith(SpringRunner.class)
public class MbglApplicationTests {

    @Autowired
    private SystemConfig systemConfig;

    @Test
    public void test2() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));

        String uri = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(headers);


        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", systemConfig.getWx().getAppid());
        params.put("secret", systemConfig.getWx().getSecret());
        params.put("js_code", "073Oh40008zhDO1mrl300GZLpg1Oh40P");

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class,params);


        String body = response.getBody();

        JSONObject jsonObject = JSONUtil.parseObj(body);

        WxJscode2sessionResult result = JSONUtil.toBean(jsonObject, WxJscode2sessionResult.class);


        System.out.println(response.getBody());
        System.out.println(result);
    }

    @Test
    public void contextLoads() {

        String appId = "wx4f4bc4dec97d474b";
        String sessionKey = "LLlbvYFJpyhpFfEVl3nyxg==";
        String encryptedData = "2SZf7CcU0+X4tLhjBv7ct6acfsT+PNOXtRsnDNIeRs0cH31hSDwBip/6wlXSREKmjdmFBsSBakUq6XAZg33qOwUCsH9uJLao1tubGhpdlsXK+SYEodF6Gu3zQEH2+ZSz7U7fQZXMiPT5Y/ACPAwYj/+kWWn8LDQ5+8+JsyThKmcY1tp3PPrtupE8TBouzpR1v8SUXpbG/adKwJWklUCz3yRWF2WJr9OUOL5eFI/Wl7xhqp/duP/DStSX4H5dGcX1aZYZSdLBzQyYpc36rTUI8VIxYuCTWsDmG2Ne1wXlr79Rqe59WHrLG/xJrXvZeHqBMfoY5wvLXZ0OhT56apgbFoFmhCd/P8Y6eTNUvDi/RS48Xs71qSzzKGpAYQczDb0AJS2UCak8z9gQZk/EbPXBMidC8eloknbBxbOpGz87uCd4hpePZ5qQo7zXrta6gdMJnFYcgiN0a/U/5z5fZq4RS5E9Ojjg5vxE2OPKhhRPru12+EqcaIrrneyrWmC+iV5iBO3TuO41BOR9ZshmrO6iCBLLTDpc5f8sy2OKjTbRImjWW9qsSEsW87u0DAOcrTqAPK2h/levjkPwm1KLfrOEkCcug/mu2+W8UZjqRHILvd6SOtYM6CqSKXA6oCt+kwulejfAOhJcp8geZYIhansrdtLculjp9LREW5GaKs+c35vYvAElNT7DN+QW0VAVnxM1fI664tGdSt822+n1Shm0SYAaBGFzoju9r1V6KrPAGGcThYK07wMht2mCRt4HPdPmHt7hGs9GYmgMjx0pIcjgKVBbDU9CBj7yo4HMC5XpMLpokUyZLhgY3ZZPcN+wm+A4diaIt9ANdeAZ0fhxCGhRtMkMRehyg/6tFuADNxgjxl+ue/JjazEy7XTuTAf6x3GI1p9TkCQGGbXvgWkeSd6K05bE67xj8KtKLqqclgQu/9VSNisk9MYZs4lSjFn7jYfi8rSN+ya0Sf6b+4gp5GcuZ3jBcG+caVG32iWijgP4ukB+DBb53Xd1YY+j3o1R6x0YQAZ2goD/A42ldIbGREVMeZwcsfxctPjG+GwkotS8sQOVL2oha/7FkxqD58rT9RIrJFYScdOJ6XyOCniBBylVEP9L6qoRbIHx/zgZy52zbgxOGCpjtEN2TNBz8agu7hTZSPWpQGXq1gmWZSjC87mdCD3V8/FdAyisKnpUjmA3OQGKmleVJphY0MegHrdOazZ7i00ZH3vzDB+aHcSLRL8luZbw7oIQ8ciXXapudkpc3+5K/JNIX/gVAcSpaHaEpU20e/Yu02dP37uk/HSycGZLIf73JzGki/XxrPAEobH/x4hVcljwQl+lPwaizflHv5coDG+FMKSDUMv8hGxZ3TLq7hYZInb75vLrW5tsTJayGS2Pb6g7uQW9Bh7Sj4t3sKR7gjSgQJPJpROf/dc6yR21AJjnMl0UqQU5hqAl4EpmK9v/fVTi+/95fN23WmI1ij+cOfqep3ZvWgH1MznbtGh3yGYcyK5ZVQNytCg5X0isUPHwz7HbfVGNfvruEbUUKyT4";
        String iv = "pnvkSyyBh8viXCOwm3hsHg==";



        WxDecodeUtil.decryptWxDataOfHutool(sessionKey, encryptedData, iv);
    }


    @Test
    public void mybatis() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mbgl?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("hhx") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\Code\\IdeaArea\\小程序后端\\mbgl\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.hhhhhx") // 设置父包名
                            .moduleName("mbgl") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\Code\\IdeaArea\\小程序后端\\mbgl\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })

                .strategyConfig(builder -> {
                    builder
                            // .addInclude("t_connect") // 设置需要生成的表名
                            // .addInclude("t_doctor")
                            // .addInclude("t_message")
                            // .addInclude("t_patient")
                            // .addInclude("t_record")
                            // .addInclude("t_user")
                            // .addInclude("t_notice")
                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
