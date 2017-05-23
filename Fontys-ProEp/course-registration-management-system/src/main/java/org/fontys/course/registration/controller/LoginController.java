package org.fontys.course.registration.controller;

import org.fontys.course.registration.model.Credentials;
import org.fontys.course.registration.service.LoginService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/login")
@Api(
        name = "Login model API",
        description = "provides the list of methods that manages the login function.",
        stage = ApiStage.RC
)
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public void Login(@RequestBody Credentials credentials) throws Exception {
        this.loginService.LoginUser(credentials.getPcn(), credentials.getPassword());
    }

}
