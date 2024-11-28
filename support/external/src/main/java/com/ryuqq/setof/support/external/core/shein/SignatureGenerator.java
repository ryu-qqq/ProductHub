package com.ryuqq.setof.support.external.core.shein;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static javax.xml.crypto.dsig.SignatureMethod.HMAC_SHA256;

@Component
public class SignatureGenerator {

    private static final String IV = "space-station-de";


    public String generateSignature(String appid, String secretKey, String apiPath, String timestamp, String randomKey) {
        try {
            // 서명 문자열 생성
            String signString = String.format("%s&%s&%s", appid, timestamp, apiPath);
            String randomSecretKey = secretKey + randomKey;

            // HMAC SHA256 해시 생성
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(randomSecretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKeySpec);

            byte[] hmacSha256 = mac.doFinal(signString.getBytes(StandardCharsets.UTF_8));

            // 해시 결과를 16진수 문자열로 변환
            String hexString = byteArrayToHexString(hmacSha256);

            // 16진수 문자열을 Base64로 인코딩
            String base64Value = Base64.getEncoder().encodeToString(hexString.getBytes(StandardCharsets.UTF_8));

            // 최종 서명 반환
            return randomKey + base64Value;
        } catch (Exception e) {
            throw new RuntimeException("Error generating signature", e);
        }
    }

    public String decrypt(String encodedSecretKey, String key) {
        try {

            byte[] encryptedContent = Base64.getDecoder().decode(encodedSecretKey);

            byte[] ivBytes = IV.getBytes(StandardCharsets.UTF_8);

            SecretKeySpec secretKeySpec = new SecretKeySpec(key.substring(0, 16).getBytes(StandardCharsets.UTF_8), "AES");

            // AES 복호화 초기화
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(ivBytes));

            // 복호화 실행
            byte[] decryptedContent = cipher.doFinal(encryptedContent);

            // 패딩 제거 후 반환
            return unpad(decryptedContent);
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt", e);
        }
    }

    private String unpad(byte[] decrypted) {
        int paddingLength = decrypted[decrypted.length - 1];
        return new String(decrypted, 0, decrypted.length - paddingLength, StandardCharsets.UTF_8);
    }

    private String byteArrayToHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
