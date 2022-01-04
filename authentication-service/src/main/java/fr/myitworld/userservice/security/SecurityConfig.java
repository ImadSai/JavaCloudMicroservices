package fr.myitworld.userservice.security;

import fr.myitworld.userservice.filter.JwtLoginFilter;
import fr.myitworld.userservice.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Definir les utilisateurs qui ont le droits d'accéder
     *
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    /**
     * Configurer les droits d'accés
     *
     * @param httpSecurity
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors()
                .and().csrf().disable()
                .authorizeRequests()

                // Path for Actuator
                .antMatchers("/actuator/**").permitAll()

                // Path for Signup
                .antMatchers("/user/signup").permitAll()

                // Path for Logout
                .antMatchers("/logout").permitAll()

                // All other Paths
                .anyRequest().authenticated()

                //  Path to Login
                .and().formLogin().loginPage("/login").permitAll()
                .and().addFilter(new JwtLoginFilter(authenticationManagerBean()));
    }
}
