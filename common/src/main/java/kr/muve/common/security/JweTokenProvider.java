package kr.muve.common.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.AeadAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import kr.muve.common.exception.BaseException;
import kr.muve.common.exception.ErrorCode;
import kr.muve.common.exception.JweException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@Component
public class JweTokenProvider {

    private final AeadAlgorithm algorithm;
    private static final String key = "X7v$K3m@Z9q#B5s!N1w%F8d^T4c&L0g*";
    public static final String AUTH_TOKEN = "authToken";
    private final ObjectMapper objectMapper;
    private final SecretKey secretKey;

    public JweTokenProvider() {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        algorithm = Jwts.ENC.A256GCM;
        secretKey = new SecretKeySpec(keyBytes, "AES");
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public String createToken(String email) {
        try {
            AuthJwe authJwe = new AuthJwe(email, LocalDateTime.now().plusDays(1));
            String content = objectMapper.writeValueAsString(authJwe);
            byte[] encryptedContent = content.getBytes(StandardCharsets.UTF_8);

            return Jwts.builder()
                    .content(encryptedContent, TEXT_PLAIN_VALUE)
                    .encryptWith(secretKey, algorithm)
                    .compact();
        } catch (JsonProcessingException e) {
            throw new JweException(ErrorCode.JWE_CYPHER_FAIL);
        }
    }

    public boolean validToken(String token) {
        try {
            AuthJwe authJwe = exportAuthJwe(token);
            checkPreConditionExpired(authJwe);

            return true;
        } catch (IOException e) {
            throw new JweException(ErrorCode.JWE_TOKEN_INVALID);
        }
    }

    public String getEmail(String token) {
        try {
            AuthJwe authJwe = exportAuthJwe(token);
            checkPreConditionExpired(authJwe);

            return authJwe.getEmail();
        } catch (IOException e) {
            throw new JweException(ErrorCode.JWE_TOKEN_INVALID);
        }
    }

    private AuthJwe exportAuthJwe(String token) throws JsonProcessingException {
        String decrypted = new String(getContent(token));
        AuthJwe authJwe = objectMapper.readValue(decrypted, AuthJwe.class);
        return authJwe;
    }

    private static void checkPreConditionExpired(AuthJwe authJwe) {
        if (authJwe.getIssuedAt().isBefore(LocalDateTime.now())) {
            throw new JweException(ErrorCode.JWE_EXPIRED);
        }
    }

    private byte[] getContent(String token) {
        try {
            JwtParser parser = Jwts.parser()
                    .decryptWith(secretKey)
                    .build();

            Jwe<byte[]> jwe = parser.parseEncryptedContent(token);
            return jwe.getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            throw new JweException(ErrorCode.JWE_CYPHER_FAIL);
        }
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}
