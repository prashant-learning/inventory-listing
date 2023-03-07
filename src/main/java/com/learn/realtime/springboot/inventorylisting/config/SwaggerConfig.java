package com.learn.realtime.springboot.inventorylisting.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 *
 *  We are telling to spring boot that consider this file as setup file or configuration
 *
 *
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info().title("Inventory Listing")
                        .description("API's to support CURD operation for inventory microservice")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("inventoryListing")
                .pathsToMatch("/**")
                .build();
    }

//    @Bean
//    public Docket publicApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("org.github.springshop.web.public"))
//                .paths(PathSelectors.regex("/public.*"))
//                .build()
//                .groupName("springshop-public")
//                .apiInfo(apiInfo());
//    }
}
