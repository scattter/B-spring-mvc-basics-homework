package com.thoughtworks.capacity.gtb.mvc.Service;

import com.thoughtworks.capacity.gtb.mvc.Component.User;
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

    public User login(String username, String password) {
        List<User> isExit = userList.stream().filter(it -> it.getUsername().equals(username)
                && it.getPassword().equals(password))
                .collect(Collectors.toList());
        return isExit.get(0);
    }

    public void register(Map<String, String> userInfo) throws Exception {
        userList.add(User.builder()
                .id(userList.size() + 1)
                .username(userInfo.get("username"))
                .password(userInfo.get("password"))
                .email(userInfo.get("email"))
                .build());
    }
}
