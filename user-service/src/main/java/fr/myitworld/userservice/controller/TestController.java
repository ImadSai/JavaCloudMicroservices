package fr.myitworld.userservice.controller;

import fr.myitworld.userservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "hello")
    public String testController(@RequestParam(value = "msg") String msg) {
        return testService.getInformation(msg);
    }

}
