package fr.myitworld.userservice.feign;

import fr.myitworld.userservice.model.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserRestClient {

    @GetMapping(path = "/users/findByEmail")
    UserVO getUserByEmail(@RequestParam(value = "email") String email);

}
