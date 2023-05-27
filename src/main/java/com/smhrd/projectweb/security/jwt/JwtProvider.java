package com.smhrd.projectweb.security.jwt;

import com.smhrd.projectweb.entity.response.api.v1.device.DeviceAuthResponse;
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
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final DeviceUserDetail deviceUserDetail;

    @Value("${security.jwt.token.secret-key-base64}")
    private String base64SecretKey;

    @Value("${security.jwt.token.access-expire-length-sec}")
    private Long accessValidityInSeconds;

    @Value("${security.jwt.token.refresh-expire-length-hour}")
    private Long refreshValidityInHours;

    private SecretKey secretKey;

    private static final String SUBJECT = "some-service";
    private static final String LOGIN_ID = "login_id";
    private static final String DEVICE_ID = "device_id";


    @PostConstruct
    protected void init() {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64SecretKey));
    }

    public DeviceAuthResponse createToken(String loginId, Long deviceId) {
        Claims claims = createClaims(loginId, deviceId);
        return new DeviceAuthResponse(createAccessToken(claims), createRefreshToken(claims));
    }

    public Claims createClaims(String loginId, Long deviceId) {
        Claims claims = Jwts.claims();
        claims.setSubject(SUBJECT);
        claims.put(LOGIN_ID, loginId);
        claims.put(DEVICE_ID, deviceId);
        return claims;
    }

    private String createAccessToken(Claims claims) {
        return createToken(claims, this.accessValidityInSeconds * 1000);
    }

    private String createRefreshToken(Claims claims) {
        return createToken(claims, this.refreshValidityInHours * 60 * 60 * 1000);
    }

    public String createToken(Claims claims, Long seconds) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + seconds);

        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity).signWith(secretKey).compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = deviceUserDetail.loadUserByUsername(getLoginId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public JwtParser getJwtParser() {
        return Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    public String getSubject(String token) {
        return getJwsClaims(token).getBody().getSubject();
    }

    public Jws<Claims> getJwsClaims(String token) {
        return getJwtParser().parseClaimsJws(token);
    }

    public String getLoginId(String token) {
        return getJwsClaims(token).getBody().get(LOGIN_ID, String.class);
    }

    public Long getDeviceId(String token) {
        return getJwsClaims(token).getBody().get(DEVICE_ID, Long.class);
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
            return Objects.equals(getSubject(token), SUBJECT);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
