package fr.myitworld.userservice.controller;

import fr.myitworld.userservice.entities.User;
import fr.myitworld.userservice.exceptions.ResourceNotFoundException;
import fr.myitworld.userservice.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "findByEmail")
    public User findUserByEmail(@RequestParam(value = "email") String email) {
        User userFound = this.userRepository.findByEmail(email);
        if (userFound == null) throw new ResourceNotFoundException("User Not found");
        return userFound;
    }

}
