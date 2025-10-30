package com.example.backend.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Model.MetaMaskLoginRequest;
import com.example.backend.until.EthereumSignatureVerifier;
import com.example.backend.until.JwtUtil;

@RestController
@RequestMapping("/metamask")
public class MetaMaskController {
    private final Map<String, String> nonceStore = new HashMap<>();
    @Autowired
    private JwtUtil jwtUtil; // ✅ Dùng JwtUtil chứ không phải jwtService

    @GetMapping("/nonce/{address}")
    public ResponseEntity<Map<String, String>> getNonce(@PathVariable("address") String address) {
        String nonce = UUID.randomUUID().toString();
        nonceStore.put(address.toLowerCase(), nonce);
        return ResponseEntity.ok(Map.of("nonce", nonce));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifySignature(@RequestBody MetaMaskLoginRequest req) {
        String address = req.getWalletAddress().toLowerCase();
        String nonce = nonceStore.get(address);
        if (nonce == null)
            return ResponseEntity.badRequest().body("No nonce found");

        String message = nonce;
        boolean valid = EthereumSignatureVerifier.verifySignature(req.getSignature(), message, address);
        System.out.println("✅ Signature valid: " + valid);

        if (!valid)
            return ResponseEntity.status(401).body("Invalid signature");

        // ✅ Sinh JWT hợp lệ
        String token = jwtUtil.generateToken(address,
                9999L,
                "wallet",0);
        nonceStore.remove(address);

        System.out.println("🎟️ Token tạo ra: " + token);
        return ResponseEntity.ok(Map.of("token", token, "role", "metamask"));
    }
}
