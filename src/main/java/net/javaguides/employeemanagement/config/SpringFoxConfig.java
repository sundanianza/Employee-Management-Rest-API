package net.javaguides.employeemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;



@Configuration
public class SpringFoxConfig   {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.javaguides.employeemanagement"))
                .paths(PathSelectors.any())
                .build().apiInfo(metaData());
    }


    private ApiInfo metaData(){
        return new ApiInfoBuilder()
                .title("Tech Interface - Spring Swagger Documetation")
                .description("\"Swagger Configuration \"")
                .version("1.1.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.anzani.com")
                .contact(new Contact("Anzani Sundani","http://www.anzani.com","099"))
                .build();
    }

}
