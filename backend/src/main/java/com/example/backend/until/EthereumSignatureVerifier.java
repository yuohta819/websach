package com.example.backend.until;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

public class EthereumSignatureVerifier {
    public static boolean verifySignature(String signature, String message, String address) {
        try {
            // 1️⃣ Thêm prefix giống MetaMask
            String prefix = "\u0019Ethereum Signed Message:\n" + message.length();
            String prefixedMessage = prefix + message;

            // 2️⃣ Hash message
            byte[] msgHash = Sign.getEthereumMessageHash(prefixedMessage.getBytes(StandardCharsets.UTF_8));

            // 3️⃣ Tách chữ ký
            byte[] signatureBytes = Numeric.hexStringToByteArray(signature);
            byte v = signatureBytes[64];
            if (v < 27) v += 27;

            byte[] r = Arrays.copyOfRange(signatureBytes, 0, 32);
            byte[] s = Arrays.copyOfRange(signatureBytes, 32, 64);

            // 4️⃣ Khôi phục địa chỉ từ chữ ký
            Sign.SignatureData sigData = new Sign.SignatureData(v, r, s);
            String recoveredAddress = "0x" + Keys.getAddress(Sign.signedPrefixedMessageToKey(message.getBytes(StandardCharsets.UTF_8), sigData));

            // 5️⃣ So sánh (địa chỉ đều lowercase)
            return recoveredAddress.equalsIgnoreCase(address);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
