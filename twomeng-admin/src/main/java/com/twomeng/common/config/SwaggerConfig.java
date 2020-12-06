package com.twomeng.common.config;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.regex.Pattern;


@Configuration // 声明是配置类
@EnableKnife4j
public class SwaggerConfig {

    @Value("${server.servlet.context-path}")
    private String contextpath;
    private ApiInfo initApiInfo(){
        ApiInfo apiInfo = new ApiInfo("renren", "welcome to renren", "1.0.0","","","","");
        return apiInfo;
    }

    @Bean // 函数返回结果直接注入spring容器中
    public Docket restfulApi(){
        System.out.println("http://localhost" + contextpath + "/swagger-ui.html");
        System.out.println("http://localhost" + contextpath + "/swagger-ui/");
        System.out.println("http://localhost" + contextpath + "/doc.html/");
        return new Docket(DocumentationType.SWAGGER_2)
//                .pathMapping(contextpath) // base，最终调用接口后会和paths拼接在一起
                .apiInfo(initApiInfo())
                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.twomeng.modules.sys.controller"))
                .paths(PathSelectors.any()) // 全部类型的url都被生成接口文档
//                .paths(allowPaths())
                .build();
    }

//    private Predicate<String> allowPaths(){
//        return Pattern.compile("/controller/.*").asPredicate();
//    }





}
