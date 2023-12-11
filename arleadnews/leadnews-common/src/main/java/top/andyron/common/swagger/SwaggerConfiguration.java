package top.andyron.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author andyron
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                // 要扫描的API(Controller)基础包
                .apis(RequestHandlerSelectors.basePackage("top.andyron"))
                .paths(PathSelectors.any())
                .build();
    }

    ;// TODO 不同微服务的怎么区分？分组？
    private ApiInfo buildApiInfo() {
        Contact contact = new Contact("Andy Ron","","rongming.2008@163.com");
        return new ApiInfoBuilder()
                .title("AR头条-API文档")
                .description("AR头条api")
                .contact(contact)
                .version("1.0.0").build();
    }
}
