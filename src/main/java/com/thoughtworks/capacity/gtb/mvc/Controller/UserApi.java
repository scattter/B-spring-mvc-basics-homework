package com.thoughtworks.capacity.gtb.mvc.Controller;

import com.thoughtworks.capacity.gtb.mvc.Component.User;
import com.thoughtworks.capacity.gtb.mvc.Exception.UserNameAlreadyExistsException;
import com.thoughtworks.capacity.gtb.mvc.Service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public User login(@RequestParam("username") String username,
                      @RequestParam("password") String password) {
        return this.userService.login(username, password);
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid User userInfo) throws Exception {
        this.userService.register(userInfo);
    }

}
