package com.sedatbsp.asu.infrastructure.configuration.swagger;

import com.sedatbsp.asu.infrastructure.common.constant.SwaggerConstant;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    /**
     * Bean declaration for Swagger Documentation details.
     *
     * @return OpenAPI. Please, see the {@link OpenAPI} class for details.
     *
     */

    @Bean
    public OpenAPI swaggerDocumentation() {
        Contact contact = new Contact();
        contact.setName("Sedat Başpınar");
        contact.setEmail("sdtbsp10@gmail.com");
        contact.setUrl("https://www.linkedin.com/in/sedatbaspinar/");
        return new OpenAPI()
                .info(new Info().title("Auction Shortened Url Service")
                        .description("Auction Shortened Url REST API")
                        .version("1.0.0")
                        .contact(contact)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Auction Shortened Url REST API Swagger UI"));
    }

    /**
     * Bean declaration for Swagger documentation details for all APIs within the {@link SwaggerConfiguration} field.
     *
     * @return GroupedOpenAPI. Please, see the {@link GroupedOpenApi} class for details.
     *
     */
    @Bean
    public GroupedOpenApi urlShortenedRestApi() {
        return GroupedOpenApi.builder()
                .group("all-api")
                .packagesToScan(SwaggerConstant.ADAPTERS_PATH)
                .build();
    }

    @Bean
    public GroupedOpenApi userRestApi() {
        return GroupedOpenApi.builder()
                .group("user-controller")
                .packagesToScan(SwaggerConstant.USER_CONTROLLER_PATH)
                .build();
    }

    @Bean
    public GroupedOpenApi urlRestApi() {
        return GroupedOpenApi.builder()
                .group("url-controller")
                .packagesToScan(SwaggerConstant.URL_CONTROLLER_PATH)
                .build();
    }


}
