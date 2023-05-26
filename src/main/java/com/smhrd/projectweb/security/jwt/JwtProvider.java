package com.smhrd.projectweb.security.jwt;

import com.smhrd.projectweb.security.DeviceUserDetail;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${security.jwt.token.secret-key-base64}")
    private String base64SecretKey;

    @Value("${security.jwt.token.expire-length-sec}")
    private Long validityInSeconds;

    private SecretKey secretKey;

    private final DeviceUserDetail deviceUserDetail;

    @PostConstruct
    protected void init() {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64SecretKey));
    }

    public String createToken(String loginId, Long deviceId) {
        Claims claims = Jwts.claims();
        claims.setSubject(loginId);
        claims.put("id", deviceId);

        Date now = new Date();
        Date validity = new Date(now.getTime() + this.validityInSeconds * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey)
                .compact();

    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = deviceUserDetail.loadUserByUsername(getLoginId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public JwtParser getJwtParser() {
        return Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    public Jws<Claims> getJwsClaims(String token) {
        return getJwtParser().parseClaimsJws(token);
    }

    public String getLoginId(String token) {
        return getJwsClaims(token).getBody().getSubject();
    }

    public Long getDeviceId(String token) {
        return getJwsClaims(token).getBody().get("id", Long.class);
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            getJwsClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
           return false;
        }
    }
}
