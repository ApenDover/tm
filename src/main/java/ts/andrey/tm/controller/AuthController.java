package ts.andrey.tm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/testUser")
    public String testUser() {
        return "ok";
    }

    @GetMapping("/testAdmin")
    public String testAdmin() {
        return "ok";
    }

}
