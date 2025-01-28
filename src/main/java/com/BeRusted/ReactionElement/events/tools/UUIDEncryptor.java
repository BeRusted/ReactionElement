package com.BeRusted.ReactionElement.events.tools;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

public class UUIDEncryptor {
    public static String encryptUUID(UUID uuid) {
        try {
            // 将 UUID 转为字符串
            String uuidStr = uuid.toString();

            // 使用 SHA-256 哈希
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(uuidStr.getBytes(StandardCharsets.UTF_8));

            // 转为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // 截取前 15 位
            return hexString.substring(0, 15);
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting UUID", e);
        }
    }
}
