package com.lewisCode.hostelbookingsystem.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("Hostel Management System")
                .description("Api allow student to choose room based on hostel of their choice")
                .version("V1.0")
                .contact(new Contact("Lewis","http:swagger-ui/api/*","swagger@gmail.io"))
                .license("Licence").build();
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                .paths(regex("/api.*"))
                .apis(RequestHandlerSelectors.basePackage("com.lewisCode"))
                .build()
                .apiInfo(apiInfo());
    }
    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}
