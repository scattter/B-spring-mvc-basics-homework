package com.thoughtworks.capacity.gtb.mvc.Service;

import com.thoughtworks.capacity.gtb.mvc.Component.User;
import com.thoughtworks.capacity.gtb.mvc.Exception.UserNameAlreadyExistsException;
import com.thoughtworks.capacity.gtb.mvc.Exception.UserNameOrPasswordIsErrorException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    List<User> userList = new ArrayList<User>();

    public UserService() {
        userList.add(new User(1, "kang", "123456", "a@b.com"));
        userList.add(new User(2, "mary", "654321", "a@c.com"));
    }

    public User login(String username, String password) throws UserNameOrPasswordIsErrorException {
        List<User> isExit = userList.stream().filter(it -> it.getUsername().equals(username)
                && it.getPassword().equals(password))
                .collect(Collectors.toList());
        if (isExit.isEmpty()) {
            throw new UserNameOrPasswordIsErrorException("用户名或密码错误");
        }
        return isExit.get(0);
    }

    public void register(User userInfo) throws UserNameAlreadyExistsException {
        List<User> isExit = userList.stream().filter(it -> it.getUsername().equals(userInfo.getUsername()))
                .collect(Collectors.toList());
        if (!isExit.isEmpty()) {
            throw new UserNameAlreadyExistsException("用户名重复");
        }
        User newUser = User.builder()
                .id(userList.size() + 1)
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .email(userInfo.getEmail())
                .build();
        userList.add(newUser);
    }
}
