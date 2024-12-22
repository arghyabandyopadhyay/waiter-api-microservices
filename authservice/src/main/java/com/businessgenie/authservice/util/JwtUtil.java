package com.businessgenie.authservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private final String secret="syUSkHwbzDJp8TCLpt2msCODsm6VnmTu3sY2EinlUDWHguNNJOyqg0kT6xlWKGIAzX/jxsx5hRTs63pvCB18I3xhtGJIif9SyRpkh1BXibI9mB6pkupxUMcE1VvS+JBRXm67h7GTYNMRwtxxLbRSDZyZq5bY8WKvrayEjrvGZeUgKuO0vjvWrbnZiPA3E6v2vtnQ5O7PUYupSPTa0S2BG+yQ0GUH4uD1pWgVNeXEIgPwBCDRjBpI9NzSCKCaKjL2qGQHWiCJxEobBwnU/zCi8zeFkkZZ5ZbAThjwBNqx80ZkZcn+DVBX/XTB+Txe35c12INitFKcRrttIWjNDywA";

    public String extractEmailId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String emailId) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, emailId);
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String emailId = extractEmailId(token);
        return (emailId.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}