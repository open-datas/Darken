package com.hone.auth.server.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置Swagger2对象
 * @author hourz
 * @since 2019-11-18
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
    /**
     *
     * 显示swagger-ui.html文档展示页，还必须注入swagger资源：
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    //可以注入多个doket，也就是多个版本的api，可以在看到有三个版本groupName不能是重复的，v1和v2是ant风格匹配，配置文件
    @Bean
    public Docket api() {
        //可以添加多个header或参数
        ParameterBuilder aParameterBuilder = new ParameterBuilder();

        aParameterBuilder
                //参数类型支持header, cookie, body, query etc
                .parameterType("header")
                //参数名
                .name("token")
                //默认值
                .defaultValue("token")
                .description("header中token字段测试")
                //指定参数值的类型
                .modelRef(new ModelRef("string"))
                //非必需，这里是全局配置，然而在登陆的时候是不用验证的
                .required(false).build();
        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(aParameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2).groupName("v1").select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/v1/**")).build().apiInfo(apiInfo1()).globalOperationParameters(aParameters);
    }


    private ApiInfo apiInfo1() {
        return new ApiInfoBuilder()
                .title("exampleApi 0.01")
                .termsOfServiceUrl("www.hone.com")
                .contact(new Contact("hourz",null,"houphiler@gmail.com"))
                .version("v1.01")
                .build();
    }
}


