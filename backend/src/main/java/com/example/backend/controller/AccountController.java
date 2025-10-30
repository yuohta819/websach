package com.example.backend.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.backend.Model.Account;
import com.example.backend.Model.Cart;
import com.example.backend.Model.Roles;
import com.example.backend.Model.UserSession;
import com.example.backend.Repository.DataRepositoryAccount;
import com.example.backend.Repository.DataRepositoryCart;
import com.example.backend.Repository.DataRepositoryRoles;
import com.example.backend.Repository.UserSessionRepository;
import com.example.backend.services.PasswordUtil;
import com.example.backend.until.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private PasswordUtil pass;
    @Autowired
    private DataRepositoryAccount account;
    @Autowired
    private JwtUtil jwtUtil; // class này bạn sẽ tạo bên dưới
    @Autowired
    private UserSessionRepository sessionRepo;
    @Autowired
    private DataRepositoryRoles roles;
    @Autowired
    private DataRepositoryCart cart;

    @PostMapping("/login/check")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data, HttpServletRequest request) {
        String name = data.get("account");
        String password = data.get("password");

        Account infor = account.getAccount(name);
        if (infor == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("account");
        }
        if (!pass.matchPassword(password, infor.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password");
        }

        // 🔹 Xóa session cũ (logout các thiết bị khác)
        sessionRepo.deleteByAccountid(infor.getAccountid());
        Roles role = roles.getAccount(infor.getAccountid());
        // 🔹 Sinh JWT token mới
        String token = jwtUtil.generateToken(
                infor.getAccount(),
                infor.getAccountid(),
                infor.getRole(),
                infor.getMoney());

        // // 🔹 Lấy IP address của client
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        // // 🔹 Lấy thông tin thiết bị từ User-Agent header
        String deviceInfo = request.getHeader("User-Agent");

        // // 🔹 Lưu session mới
        UserSession session = new UserSession();
        session.setAccountid(infor.getAccountid());
        session.setToken(token);
        session.setCreated_at(new Date());
        session.setIp_address(ipAddress);
        session.setDevice_info(deviceInfo);
        sessionRepo.save(session);

        // // 🔹 Trả về token + thông tin tài khoản
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("accountid", infor.getAccountid());
        response.put("account", infor.getAccount());
        response.put("role", infor.getRole());
        response.put("ipAddress", ipAddress);
        response.put("deviceInfo", deviceInfo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/token")
    public UserSession getMethodName(@RequestParam String param) {
        return sessionRepo.findToken(param);
    }

    @PostMapping("/register/save")
    public String postMethodName(@RequestBody Map<String, String> entity) {
        String name = entity.get("name");
        String email = entity.get("email");
        Account checkEmail = account.getAccount(email);
        if (checkEmail != null) {
            return "same";
        }
        String passsword = entity.get("password");
        String hashpass = pass.hashPassword(passsword);
        Account saveAccount = new Account();
        saveAccount.setAccount(name);
        saveAccount.setRole("CLIENT");
        saveAccount.setEmail(email);
        saveAccount.setPassword(hashpass);
        // account.save(saveAccount);
        Account savedAccount = account.save(saveAccount);

        Roles role = new Roles();
        role.setAccount(savedAccount);
        roles.save(role);
        return "";
    }

    @PostMapping("/admin/save")
    public String postMethodNamee(@RequestBody Map<String, String> entity) {
        String name = entity.get("name");
        String email = entity.get("email");
        Account checkEmail = account.getAccount(email);
        if (checkEmail != null) {
            return "same";
        }
        String passsword = entity.get("password");
        String hashpass = pass.hashPassword(passsword);
        Account saveAccount = new Account();
        saveAccount.setAccount(name);
        saveAccount.setRole("ADMIN");
        saveAccount.setEmail(email);
        saveAccount.setPassword(hashpass);
        account.save(saveAccount);
        Account savedAccount = account.save(saveAccount);

        Roles role = new Roles();
        role.setAccount(savedAccount);
        roles.save(role);
        return "";
    }

    @GetMapping("/package")
    public Roles getMethodNamee(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("authorization");
            String token = authHeader.substring(7); // bỏ chữ Bearer + khoảng trắng
            if (!jwtUtil.validateToken(token)) {
                return null;
            }
            Claims claims = jwtUtil.extractAllClaims(token);
            Long accountId = claims.get("accountid", Long.class);
            return roles.getAccount(accountId);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/package/save")
    public String getMethodNamer(@RequestParam String param, HttpServletRequest request) {
        String authHeader = request.getHeader("authorization");
        String token = authHeader.substring(7); // bỏ chữ Bearer + khoảng trắng
        if (!jwtUtil.validateToken(token)) {
            return "error";
        }
        Claims claims = jwtUtil.extractAllClaims(token);
        Integer accountId = claims.get("accountid", Integer.class);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plus30Days = now.plusDays(30);

        Date createdAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Date expiredAt = Date.from(plus30Days.atZone(ZoneId.systemDefault()).toInstant());

        if (param.equalsIgnoreCase("pro")) {
            Integer currentMoney = account.findMoneyByAccountId(accountId); // 🔹 Lấy số tiền hiện có
            if (currentMoney != null && currentMoney >= 20000) {
                account.updateMoney(currentMoney - 20000, accountId); // 🔹 Trừ tiền
                roles.updateRolePro(accountId, expiredAt, createdAt); // 🔹 Cập nhật role
            } else {
                return "not"; // hoặc throw new Exception("Không đủ tiền")
            }
        }

        if (param.equalsIgnoreCase("premium")) {
            // Lấy Roles hiện tại (Pro) nếu có
            Roles rol = cart.checkPro(accountId);

            // Lấy số tiền hiện tại
            Integer currentMoney = account.findMoneyByAccountId(accountId);
            if (currentMoney == null)
                currentMoney = 0;

            // TODO: thay bằng giá gói Premium thực tế (lấy từ DB nếu cần)
            Integer premiumCost = 129900; // ví dụ: replace with real price

            if (rol != null && rol.getExpired_date() != null) {
                Date expired = rol.getExpired_date();
                Date now1 = new Date();

                long diffMillis = expired.getTime() - now1.getTime();
                long daysLeft = diffMillis / (1000L * 60 * 60 * 24);

                // 1) Nếu đủ điều kiện, cộng tiền vào tài khoản (bonus)
                Integer bonus = 0;
                if (daysLeft > 20) {
                    bonus = 20000;
                } else if (daysLeft > 10) {
                    bonus = 10000;
                }

                if (bonus > 0) {
                    // cộng tiền: currentMoney + bonus
                    Integer newMoneyAfterBonus = currentMoney + bonus;
                    account.updateMoney(newMoneyAfterBonus, accountId);
                    currentMoney = newMoneyAfterBonus; // cập nhật giá trị hiện tại
                }

                // 2) Sau khi (có thể) cộng bonus, thực hiện logic trừ tiền để mua Premium
                if (currentMoney >= premiumCost) {
                    Integer afterDeduct = currentMoney - premiumCost;
                    account.updateMoney(afterDeduct, accountId);

                    // cập nhật role
                    roles.updateRolePremium(accountId, expiredAt, createdAt);
                    return "ok";
                } else {
                    // không đủ tiền sau khi cộng bonus
                    System.out.println("❌ Không đủ tiền để nâng cấp Premium sau khi cộng bonus!");
                    return "not";
                }

            } else {
                // rol == null -> thực hiện luôn logic trừ tiền (không cộng bonus)
                if (currentMoney >= premiumCost) {
                    Integer afterDeduct = currentMoney - premiumCost;
                    account.updateMoney(afterDeduct, accountId);

                    // cập nhật role
                    roles.updateRolePremium(accountId, expiredAt, createdAt);
                    return "ok";
                } else {
                    System.out.println("❌ Không đủ tiền để nâng cấp Premium!");
                    return "not";
                }
            }
        }

        return new String();
    }

    @GetMapping("/money")
    public Integer getMoney(@RequestParam Integer id, HttpServletRequest request) {
        String authHeader = request.getHeader("authorization");
        String token = authHeader.substring(7); // bỏ chữ Bearer + khoảng trắng
        if (!jwtUtil.validateToken(token)) {
            return 0;
        }
        Claims claims = jwtUtil.extractAllClaims(token);
        Integer accountId = claims.get("accountid", Integer.class);
        Integer money = account.findMoneyByAccountId(accountId);
        return money;
    }

    @GetMapping("/package/change")
    public String getMethodNameeeee(@RequestParam Integer param) {
        roles.updateAll(param);
        return new String();
    }

    @GetMapping("/admin/permissions")
    public List<Roles> getMethodNameeeeee() {
        return roles.findAll();
    }

    @GetMapping("/call")
    public List<Account> getMethodNameeee() {
        return account.findAll();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> updateAccountStatus(@PathVariable("id") Long id,
            @RequestBody Map<String, Object> payload) {
        try {
            // Lấy giá trị is_deleted từ body JSON
            Boolean isDeleted = (Boolean) payload.get("is_deleted");

            Optional<Account> optionalAccount = account.findById(id);
            if (optionalAccount.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Không tìm thấy tài khoản"));
            }

            Account acc = optionalAccount.get();
            acc.setIs_deleted(isDeleted);
            account.save(acc);

            return ResponseEntity.ok(Map.of(
                    "message", "Cập nhật trạng thái thành công",
                    "is_deleted", acc.getIs_deleted()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> getMethodNamme(@RequestBody Map<String, String> data, HttpServletRequest request) {
        String name = data.get("account");
        String password = data.get("password");

        Account infor = account.getAccount(name);
        if (infor == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("account");
        }
        if (!pass.matchPassword(password, infor.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password");
        }

        // 🔹 Xóa session cũ (logout các thiết bị khác)
        sessionRepo.deleteByAccountid(infor.getAccountid());
        Roles role = roles.getAccount(infor.getAccountid());
        // 🔹 Sinh JWT token mới
        String token = jwtUtil.generateToken(
                infor.getAccount(),
                infor.getAccountid(),
                infor.getRole(),
                infor.getMoney());

        // // 🔹 Lấy IP address của client
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        // // 🔹 Lấy thông tin thiết bị từ User-Agent header
        String deviceInfo = request.getHeader("User-Agent");

        // // 🔹 Lưu session mới
        UserSession session = new UserSession();
        session.setAccountid(infor.getAccountid());
        session.setToken(token);
        session.setCreated_at(new Date());
        session.setIp_address(ipAddress);
        session.setDevice_info(deviceInfo);
        sessionRepo.save(session);

        // // 🔹 Trả về token + thông tin tài khoản
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("accountid", infor.getAccountid());
        response.put("account", infor.getAccount());
        response.put("role", infor.getRole());
        response.put("ipAddress", ipAddress);
        response.put("deviceInfo", deviceInfo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/decode")
    public ResponseEntity<?> decodeToken(HttpServletRequest request) {
        try {
            // Lấy header Authorization: "Bearer <token>"
            String authHeader = request.getHeader("authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thiếu token trong header!");
            }

            // Bỏ chữ "Bearer " (7 ký tự)
            String token = authHeader.substring(7);

            // Kiểm tra token hợp lệ
            if (!jwtUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token không hợp lệ!");
            }

            // Giải mã token
            Claims claims = jwtUtil.extractAllClaims(token);

            // 🔹 Lấy thông tin quyền (role)
            String role = claims.get("role", String.class);

            // 🔹 Trả về JSON chứa thông tin quyền
            Map<String, Object> response = new HashMap<>();
            response.put("role", role);
            response.put("account", claims.get("account", String.class));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi giải mã token!");
        }
    }

}
