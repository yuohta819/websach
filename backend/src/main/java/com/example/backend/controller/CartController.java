package com.example.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Model.Cart;
import com.example.backend.Repository.DataRepositoryCart;
import com.example.backend.until.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private DataRepositoryCart cart;
    @Autowired
    private JwtUtil jwtUntil;

    @GetMapping("/call")
    public List<Cart> getMethodName(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("authorization");
            String token = authHeader.substring(7); // bỏ chữ Bearer + khoảng trắng
            if (!jwtUntil.validateToken(token)) {
                return null;
            }
            Claims claims = jwtUntil.extractAllClaims(token);
            Integer accountId = claims.get("accountid", Integer.class);
            return cart.getAllFind(accountId);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/save")
    public String postMethodName(@RequestBody Map<String, String> entity, HttpServletRequest request) {
        try {
            String bookid = entity.get("bookid");
            String roles = entity.get("roles");

            String authHeader = request.getHeader("authorization");
            String token = authHeader.substring(7); // bỏ chữ Bearer + khoảng trắng
            if (!jwtUntil.validateToken(token)) {
                return "error";
            }
            Claims claims = jwtUntil.extractAllClaims(token);
            Cart savedata = new Cart();
            Integer accountId = claims.get("accountid", Integer.class);
            if (!cart.getAllFind(accountId).isEmpty()) {

                return "same";
            }
            savedata.setBookid(Integer.parseInt(bookid));
            savedata.setAccountid(accountId);
            cart.save(savedata);

            return "success";
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
    }

    @GetMapping("/remove")
    public String getMethodName(@RequestParam String param, HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("authorization");
            String token = authHeader.substring(7); // bỏ chữ Bearer + khoảng trắng
            if (!jwtUntil.validateToken(token)) {
                return "error";
            }
            cart.deleteCart(Integer.parseInt(param));
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/check/roles")
    public String getMethodNamee(@RequestBody Map<String, String> entity, HttpServletRequest request) {
        try {
            // Quyền của sách
            String roles = entity.get("roles");

            String authHeader = request.getHeader("authorization");
            String token = authHeader.substring(7); // bỏ chữ Bearer + khoảng trắng
            if (!jwtUntil.validateToken(token)) {
                return "error";
            }

            Claims claims = jwtUntil.extractAllClaims(token);
            Integer accountId = claims.get("accountid", Integer.class);
            if (roles.toLowerCase().equalsIgnoreCase("member")) {
                if (cart.checkMember(accountId) == null) {
                    return "not roles";
                }
            }
            if (roles.toLowerCase().equalsIgnoreCase("pro")) {
                if (cart.checkPro(accountId) == null) {
                    return "not roles";
                }
            }
            if (roles.toLowerCase().equalsIgnoreCase("premium")) {
                if (cart.checkPremium(accountId) == null) {
                    return "not roles";
                }
            }
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
