package cn.shiyang.tflz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableOpenApi
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.show}")
    private boolean isSwaggerShow;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(isSwaggerShow)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "天府绿洲",
                "天府绿洲项目",
                "V 0.0.1",
                "terms of service URL",
                new Contact("shiyang", "localhost", "cnshiyang@163.com"),
                "License of API",
                "license URL",
                Collections.emptyList()
        );
    }
}
