package hu.flowacademy.login.demo.controller;

import hu.flowacademy.login.demo.entity.User;
import hu.flowacademy.login.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Secured("ROLE_USER")
    @GetMapping("users")
    public List<User> users() {
        return userService.getUsers();
    }

    @PostMapping(value = "user", consumes="application/json")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

}
