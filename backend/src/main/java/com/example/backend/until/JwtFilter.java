package com.example.backend.until;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.backend.Model.Account;
import com.example.backend.Model.UserSession;
import com.example.backend.Repository.DataRepositoryAccount;
import com.example.backend.Repository.UserSessionRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private DataRepositoryAccount accountRepo;
    @Autowired
    private UserSessionRepository sessionRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        try { // 🚫 Bỏ qua kiểm tra token cho các endpoint công khai
            if (path.startsWith("/api/") || path.startsWith("/category/") ||
                    path.startsWith("/account/") || path.startsWith("/book/") || path.startsWith("/cart/")
                    || path.startsWith("/token/only")) {
                chain.doFilter(request, response);
                return;
            }
            final String authHeader = request.getHeader("authorization");
            String username1 = request.getParameter("account");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {

                response.getWriter().write("{\"error\": \"Token invalid or expired\"}");
                return;
            }

            final String token = authHeader.substring(7);
            if (!jwtUtil.validateToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 🔹 1. báo lỗi
                response.setContentType("application/json;charset=UTF-8"); // 🔹 2. kiểu JSON + encoding
                response.getWriter().write("{\"error\": \"Token invalid or expired\"}"); // 🔹 3. nội dung JSON
                response.getWriter().flush(); // 🔹 4. đảm bảo dữ liệu thực sự gửi đi
                response.getWriter().close(); // kết thúc luồng xuất
                return; // 🔹 5. dừng filter
            }
            final String username = jwtUtil.extractUsername(token);
            if (!jwtUtil.validateToken(token, username1)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 🔹 1. báo lỗi
                response.setContentType("application/json;charset=UTF-8"); // 🔹 2. kiểu JSON + encoding
                response.getWriter().write("{\"error\": \"Token invalid or expired\"}"); // 🔹 3. nội dung JSON
                response.getWriter().flush(); // 🔹 4. đảm bảo dữ liệu thực sự gửi đi
                response.getWriter().close(); // kết thúc luồng xuất
                return; // 🔹 5. dừng filter
            }
            if (username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                // 🔍 Kiểm tra token này có trong DB không
                Optional<UserSession> currentSession = sessionRepo.findByToken(token);
                if (currentSession.isEmpty()) {
                    // Token không tồn tại (đã bị xóa vì đăng nhập nơi khác)
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Session expired or logged in elsewhere");
                    return;
                }

                // Token hợp lệ -> xác thực
                if (jwtUtil.validateToken(token, username)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
                            null,
                            new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
