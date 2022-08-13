package com.sedatbsp.asu.infrastructure.configuration.security.jwt;

import com.sedatbsp.asu.domain.common.exception.UrlBusinessException;
import com.sedatbsp.asu.infrastructure.configuration.security.CustomUserDetailsService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.sedatbsp.asu.infrastructure.common.utils.SecurityUtils.AUTH_HEADER;
import static com.sedatbsp.asu.infrastructure.common.utils.SecurityUtils.AUTH_TOKEN_PREFIX;

@Component
@RequiredArgsConstructor
public class JwtProvider{

    private final CustomUserDetailsService customUserDetailsService;

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;

    public String generateToken(String username, Long id) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    protected Authentication getAuthentication(String token) {
        final UserDetails user = customUserDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(token).getBody().getSubject();
    }

    protected String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTH_HEADER);

        if(StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    protected boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException exception) {
            throw new UrlBusinessException("expired.or.invalid.jwt.token");
        }
    }
}
