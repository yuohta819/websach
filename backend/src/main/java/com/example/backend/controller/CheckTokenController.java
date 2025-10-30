package com.example.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Repository.UserSessionRepository;
import com.example.backend.until.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/token")
public class CheckTokenController {
    @Autowired
    private UserSessionRepository token;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/check")
    public ResponseEntity<?> getMethodName(HttpServletRequest request, @RequestParam String account) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing token");
        }

        String tokenn = authHeader.substring(7); // cắt bỏ "Bearer "

        try {

            // Giải mã token
            Claims claims = jwtUtil.extractAllClaims(tokenn);
            String username = claims.get("account", String.class);
            String role = claims.get("role", String.class);
            String type = claims.get("type", String.class);
            Integer accountId = claims.get("accountid", Integer.class);

            // // Trả thông tin user
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("account", username);
            userInfo.put("role", role);
            userInfo.put("type", type);
            userInfo.put("accountid", accountId);

            return ResponseEntity.ok(userInfo);
            // return null;

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    @GetMapping("/only")
    public String getMethodName(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return "";
        }

        String tokenn = authHeader.substring(7); // cắt bỏ "Bearer "
        if (token.findToken(tokenn) == null) {
            return "error";
        }
        return new String();
    }

}
