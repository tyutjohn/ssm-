package com.tyutjohn.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //配置docket的bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    //配置apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact=new Contact("johnwang","www.tyutjohn.com","tyutjohnwang@163.com");
        return new ApiInfo(
          "johnwang的SwaggerAPI文档",
                "记录swagger",
                "V1.0",
                "www.tyutjohn.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
