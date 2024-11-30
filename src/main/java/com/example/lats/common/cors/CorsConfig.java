package com.example.lats.common.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Allow all paths
                        .allowedOrigins("http://localhost:3000")  // Ensure this matches the front-end origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Ensure methods are correct
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(true);  // Allow credentials (e.g., cookies)
            }
        };
    }
}

