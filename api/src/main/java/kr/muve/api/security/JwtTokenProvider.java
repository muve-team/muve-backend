package kr.muve.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final long TWELVE_HOURS_IN_MILLISECONDS = 1000 * 60 * 60 * 12;
    private final SecretKey signingKey;
    private final long expirationInMilliseconds;

    public JwtTokenProvider() {
        this.signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        this.expirationInMilliseconds = TWELVE_HOURS_IN_MILLISECONDS;
    }

    public String createToken(String payload) {
        Claims claims = Jwts.claims().setSubject(payload);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationInMilliseconds);
        return String.format("Bearer %s", Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(signingKey)
                .compact());
    }

    public String getSubject(String token) {
        return getClaimsJws(token).getBody().getSubject();
    }

    public boolean isValidToken(String token) {
        try {
            getClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Jws<Claims> getClaimsJws(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey.getEncoded())
                .build()
                .parseClaimsJws(token);
    }

    public String resolveToken(HttpServletRequest request) {
        // Authorization 헤더에서 Bearer 토큰 추출
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 부분 제거
        }

        return null;
    }
}
