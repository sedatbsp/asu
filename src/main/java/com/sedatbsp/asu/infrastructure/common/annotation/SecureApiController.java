package com.sedatbsp.asu.infrastructure.common.annotation;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping("/api-url")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public @interface SecureApiController {
}
