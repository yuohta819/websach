package com.example.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${frontend_url}")
    private String frontendUrl; // ví dụ: https://your-frontend.onrender.com

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 🔹 Cho phép tất cả đường dẫn trong frontend gọi đến
                .allowedOriginPatterns(frontendUrl, "http://localhost:*", "https://localhost:*")
                // 🔹 Các phương thức cho phép
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 🔹 Header cho phép gửi từ client
                .allowedHeaders("*") // dùng * để tránh quên header Authorization, Content-Type, v.v.
                // 🔹 Header mà client có thể đọc lại từ response
                .exposedHeaders("Authorization", "Content-Disposition")
                // 🔹 Cho phép gửi cookie/token nếu cần
                .allowCredentials(true);
    }
}
