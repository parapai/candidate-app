package com.vai.test.interceptor;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Component
public class JwtInterceptor implements HandlerInterceptor {
	
	@Value("${jwt.secret}")
    private String secretString;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"Akses ditolak: Token tidak ditemukan!\"}");
            return false;
        }

        String token = authHeader.substring(7);

        try {
        	Key key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));

            // 3. Bongkar dan Validasi Token. Jika token palsu/expired, baris ini otomatis melempar Exception!
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // 4. Titipkan informasi user yang login ke objek request agar bisa dibaca Controller
            request.setAttribute("currentUsername", claims.getSubject());
            request.setAttribute("currentUserId", claims.get("userId").toString());
            return true; 
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"Akses ditolak: Token tidak valid atau kedaluwarsa!\"}");
            return false;
        }
    }
}
