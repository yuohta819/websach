package com.example.backend.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Hàm mã hoá mật khẩu khi đăng ký
    public String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    // Hàm kiểm tra mật khẩu khi đăng nhập
    public boolean matchPassword(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
}