package fr.myitworld.userservice.filter;

import com.google.gson.Gson;
import feign.FeignException;
import feign.RetryableException;
import fr.myitworld.userservice.exceptions.ErrorDetails;
import fr.myitworld.userservice.service.TokenAuthenticationService;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    // Injection
    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (authenticate == null) throw new BadCredentialsException("Bad Credentials");
        return authenticate;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        User user = (User) authResult.getPrincipal();
        TokenAuthenticationService
                .addAuthentication(response, user);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {

        Throwable exception = authenticationException.getCause();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        String informationJson = "";

        if (exception instanceof RetryableException) {
            response.setStatus(503);
            informationJson = gson.toJson(new ErrorDetails(new Date(), exception.getMessage(), exception.getMessage()), ErrorDetails.class);
        } else if (exception instanceof FeignException) {
            if (((FeignException) exception).status() == 404) {
                response.setStatus(403);
                informationJson = gson.toJson(new ErrorDetails(new Date(), "User Not found", "Username or email not found"), ErrorDetails.class);
            } else if (((FeignException) exception).status() == 503) {
                response.setStatus(503);
                informationJson = gson.toJson(new ErrorDetails(new Date(), "Authenticate service down", "Auth Service is down !"), ErrorDetails.class);
            }
        } else {
            response.setStatus(403);
            informationJson = gson.toJson(new ErrorDetails(new Date(), authenticationException.getMessage(), authenticationException.getMessage()), ErrorDetails.class);
        }

        response.getWriter().write(informationJson);
        response.getWriter().close();
    }

}
