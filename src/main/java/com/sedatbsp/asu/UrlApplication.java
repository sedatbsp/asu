package com.sedatbsp.asu;

import com.sedatbsp.asu.infrastructure.configuration.cors.WebConfiguration;
import com.sedatbsp.asu.infrastructure.configuration.localization.LocalizationConfiguration;
import com.sedatbsp.asu.infrastructure.configuration.security.WebSecurityConfiguration;
import com.sedatbsp.asu.infrastructure.configuration.swagger.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {
        SwaggerConfiguration.class,
        WebConfiguration.class,
        WebSecurityConfiguration.class,
        LocalizationConfiguration.class
})
public class UrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlApplication.class, args);
    }

}
