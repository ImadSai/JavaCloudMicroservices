package fr.myitworld.userservice.service;

import fr.myitworld.userservice.feign.UserRestClient;
import fr.myitworld.userservice.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRestClient userRestClient;

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserVO userVO = null;
        userVO = userRestClient.getUserByEmail(email);
        return User.withUsername(userVO.getEmail())
                .password("{bcrypt}" + userVO.getPassword())
                .roles("USER")
                .build();
    }

}
