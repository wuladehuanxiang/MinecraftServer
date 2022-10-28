package module.common.swagger;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * swagger自动配置
 * @author : Dragon丿Z
 * @date : 2022/10/28 14:20
 */
@Configuration
@EnableOpenApi
@ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
public class SwaggerlAutoConfigure {
    @Value("${spring.application.name}")
    private String projectName;

    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }

    private final static String SERVER_AUTH_MSG = "认证中心：'Basic '+ Base64(客户端编码:客户端密钥)，例如：Basic aW90cDppb3Rw";
    private final static String CLIENT_AUTH_MSG = "服务认证：'bearer '+ token字符串，例如：bearer 69041cdc-56bf-43dd-a60d-0bc37763d92c";

    @Bean
    public Docket api(SwaggerProperties swaggerProperties) {
        //请求体内容
        List<SecurityContext> securityContexts = Collections.singletonList(SecurityContext.builder()
                .securityReferences(Collections.singletonList(SecurityReference.builder()
                        .reference("Authorization")
                        .scopes(new AuthorizationScope[]{new AuthorizationScope("global",
                                "accessEverything")}).build()))
                .build());
        //安全方案
        List<SecurityScheme> securitySchemes = Collections.singletonList(new ApiKey("Authorization", "Authorization", "header"));

        return new Docket(DocumentationType.OAS_30)
                //添加token的参数
                .securitySchemes(securitySchemes)
                .securityContexts(securityContexts)
                .apiInfo(apiInfo(swaggerProperties))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                //添加全局Header参数
                .globalRequestParameters(Collections.singletonList(new RequestParameterBuilder()
                        .name("Authorization")
                        .description("mc-server".equals(projectName) ? SERVER_AUTH_MSG : CLIENT_AUTH_MSG)
                        .required(true)
                        .in(ParameterType.HEADER)
                        .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                        .required(true)
                        .build()));
    }

    private ApiInfo apiInfo(SwaggerProperties swaggerProperties)
    {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(), swaggerProperties.getContact().getEmail()))
                .version(swaggerProperties.getVersion())
                .build();
    }
}
