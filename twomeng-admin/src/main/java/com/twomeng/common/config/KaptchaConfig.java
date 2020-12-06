package com.twomeng.common.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 验证码的配置文件
 * 将DefaultKaptcha对象注入spring容器中，便于在SysLoginController中获取
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha defaultKaptcha(){
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "5");
        properties.put("kaptcha.textproducer.font.names", "Arial,Courier,cmr10,宋体,楷体,微软雅黑");
        // kaptcha的Config类，接收一个存储了验证码图片参数的properties对象
        // properties可设置的参数可以查看Config类的方法，每个方法定义了一个可修改配置项
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        // DefaultKaptcha类实现了producer接口：createImg, createText
        // DefaultKaptcha类继承自Configurable类：setConfig, getConfig
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
