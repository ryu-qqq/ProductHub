package com.ryuqq.setof.support.external.core.shein;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class SignatureGenerator {

    private static final String IV = "space-station-de";

    public String generateSignature(String appId, String secretKey, String apiPath, String timestamp, String randomKey) {
        try {

            String signString = String.format("%s&%s&%s", appId, timestamp, apiPath);
            String randomSecretKey = secretKey + randomKey;


            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(randomSecretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hmacSha256 = mac.doFinal(signString.getBytes(StandardCharsets.UTF_8));


            StringBuilder hexString = new StringBuilder();
            for (byte b : hmacSha256) {
                hexString.append(String.format("%02x", b));
            }


            String base64Value = Base64.getEncoder().encodeToString(hexString.toString().getBytes(StandardCharsets.UTF_8));


            return randomKey + base64Value;
        } catch (Exception e) {
            throw new RuntimeException("Error generating signature", e);
        }
    }


    public String decrypt(String encodedSecretKey, String secretKey) {
        try {
            byte[] encryptedContent = Base64.getDecoder().decode(encodedSecretKey);
            byte[] ivBytes = IV.getBytes(StandardCharsets.UTF_8);

            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.substring(0, 16).getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(ivBytes));
            byte[] decryptedContent = cipher.doFinal(encryptedContent);

            return new String(decryptedContent, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt", e);
        }
    }

}
