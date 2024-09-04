package com.grupo1.ganaderiagrupo1.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // Permitir t odo temporalmente
                .allowedOrigins("*") // Permitir todo temporalmente
                .allowedMethods("*") // Permitir todos los métodos
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
    }
}

