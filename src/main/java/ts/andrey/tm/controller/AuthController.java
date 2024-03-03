package ts.andrey.tm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ts.andrey.tm.tmconst.ApiConst;

@RestController
@RequestMapping(ApiConst.API)
public class AuthController {

    @GetMapping(ApiConst.USER_TEST)
    public String testUser() {
        return "ok";
    }

    @GetMapping(ApiConst.ADMIN_TEST)
    public String testAdmin() {
        return "ok";
    }

}
