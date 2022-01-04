package fr.myitworld.userservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.stream.Collectors;

public class TokenAuthenticationService {

    private static final long EXPIRATIONTIME = 86400000; // 1 jour
    private static final String SECRET = "secretCodeProject";
    private static final String TOKEN_PREFIX = "Bearer";

    public static void addAuthentication(HttpServletResponse res, User user) {

        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        String jwtAccessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .withClaim("roles", user.getAuthorities().stream().map(ga -> ga.getAuthority()).collect(Collectors.toList()))
                .sign(algorithm);

        res.addHeader("Authorization", TOKEN_PREFIX + " " + jwtAccessToken);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            if (token != null) {
                // parse the token.
                String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                        .build()
                        .verify(token.replace(TOKEN_PREFIX, ""))
                        .getSubject();

                return user != null ? new UsernamePasswordAuthenticationToken(user, null) : null;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

}
