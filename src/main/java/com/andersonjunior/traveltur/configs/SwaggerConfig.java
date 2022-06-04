package com.andersonjunior.traveltur.configs;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.service.ApiInfo;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.andersonjunior.traveltur")).paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo("TravelTur", "API Rest", "1.0",
                "Termos de Serviço",
                new springfox.documentation.service.Contact("Anderson Júnior",
                        "https://www.linkedin.com/in/andersonjuniorworks/", "andersonjunior.dev@gmail.com"),
                "Apache License Version 2.0", "https://www.apache.org/licesen.html", new ArrayList<>());

        return apiInfo;
    }

}
