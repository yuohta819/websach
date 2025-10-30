package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 🔹 Bật CORS trước khi disable CSRF
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // 🚫 Tắt CSRF cho REST API
            .csrf(csrf -> csrf.disable())
            // 🔓 Cho phép mọi endpoint (nếu bạn có JWT filter thì đổi lại sau)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            // ⚙️ Không dùng session
            .sessionManagement(session -> session.disable());

        return http.build();
    }

    // ✅ Cấu hình CORS để trình duyệt gửi được header Authorization
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // Cho phép origin (frontend)
        config.setAllowedOriginPatterns(Arrays.asList("*"));

        // Cho phép các header
        config.setAllowedHeaders(Arrays.asList("*"));
        // Cho phép các method
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Cho phép gửi credentials (nếu có cookie/token)
        config.setAllowCredentials(true);
        // Header client có thể đọc từ response
        config.setExposedHeaders(Arrays.asList("Authorization", "Content-Disposition"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    // ✅ Mã hóa mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
