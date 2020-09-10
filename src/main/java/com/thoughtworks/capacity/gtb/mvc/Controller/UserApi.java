package com.thoughtworks.capacity.gtb.mvc.Controller;

import com.thoughtworks.capacity.gtb.mvc.Component.User;
import com.thoughtworks.capacity.gtb.mvc.Exception.UserNameAlreadyExistsException;
import com.thoughtworks.capacity.gtb.mvc.Service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Map;

@RestController
@Validated
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    final String NAME_PATTERN = "^[0-9a-zA-Z_]{3,10}$";
    final String NAME_MESSAGE = "用户名不合法";
    final String PWD_MESSAGE = "密码不合法";


    @GetMapping("/login")
    public User login(@RequestParam("username") @Pattern(regexp = NAME_PATTERN, message = NAME_MESSAGE) String username,
                      @RequestParam("password") @Size(min = 5, max = 12, message = PWD_MESSAGE) String password) {
        return this.userService.login(username, password);
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid User userInfo) {
        this.userService.register(userInfo);
    }

}
