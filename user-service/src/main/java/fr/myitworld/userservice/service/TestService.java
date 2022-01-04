package fr.myitworld.userservice.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String getInformation(String msg) {
        return this.message(msg);
    }

    private String message(String theMessage) {
        return theMessage;
    }

}
