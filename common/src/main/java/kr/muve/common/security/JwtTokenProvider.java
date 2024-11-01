package kr.muve.common.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import kr.muve.common.exception.BaseException;
import kr.muve.common.exception.ErrorCode;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final long TWELVE_HOURS_IN_MILLISECONDS = 1000 * 60 * 60 * 12;
    private final SecretKey signingKey;
    private static final String secretKey = "3xQ56j6fBhZWyJ5lM9odZ5RdmVf3c3w1GJZUJ8+Gj7c=";
    private final long expirationInMilliseconds;
    public static final String AUTH_TOKEN = "authToken";

    public JwtTokenProvider() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
        this.expirationInMilliseconds = TWELVE_HOURS_IN_MILLISECONDS;
    }

    public String createToken(String payload) {
        Claims claims = Jwts.claims().setSubject(payload);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(signingKey)
                .compact();
    }

    public String getSubject(String token) {
        return getClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isValidToken(String token) {
        try {
            getClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new BaseException(ErrorCode.JWT_TOKEN_INVALID);
        }
    }

    private Jws<Claims> getClaimsJws(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey.getEncoded())
                .build()
                .parseClaimsJws(token);
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}
