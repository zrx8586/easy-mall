package com.leapstack.wanglong.mall.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
@Slf4j
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //为当前包下controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.leapstack.wanglong.mall.controller"))
                .paths(PathSelectors.any())
                .build()
                //添加登录认证
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("后端接口标题")
                .description("后端接口描述")
                .version("1.0")
                .build();
    }

    /**
     * 通过Swagger2的securitySchemes配置全局参数：
     * securitySchemes的ApiKey中增加一个名为“Authorization”，type为“header”的参数。
     * **/
    private List<ApiKey> securitySchemes() {
        //设置请求头信息
        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        result.add(apiKey);
        return result;
    }

    /**
     * 在Swagger2的securityContexts中通过正则表达式，
     * 设置需要使用参数的接口（或者说，是去除掉不需要使用参数的接口），如下列代码所示，通过PathSelectors.regex("^(?!auth).*$")，所有包含"auth"的接口不需要使用securitySchemes。即不需要使用上文中设置的名为“Authorization”，type为“header”的参数。
     * **/
    private List<SecurityContext> securityContexts() {
        //设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/brand/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }

}
