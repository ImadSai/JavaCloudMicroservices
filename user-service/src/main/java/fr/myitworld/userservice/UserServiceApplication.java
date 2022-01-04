package fr.myitworld.userservice;

import fr.myitworld.userservice.entities.User;
import fr.myitworld.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserRepository userRepository) {

        // Password Encoder
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return args -> {
            userRepository.save(new User(null, "Imad", "Salki", "imad@hotmail.fr", passwordEncoder.encode("1234"), new Date()));
            userRepository.save(new User(null, "Laura", "Miniot", "laura@hotmail.fr", passwordEncoder.encode("1234"), new Date()));
            userRepository.save(new User(null, "Meriem", "Salki", "meriem@hotmail.fr", passwordEncoder.encode("1234"), new Date()));
        };
    }
}
