package com.wiki.controller;

import com.wiki.model.User;
import com.wiki.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/User")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public List<User> findAllUser() {
        return service.findAllUser();
    }

    @PostMapping("saveUser")
    public User saveUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping("findByAppMail")
    public User findByAppMail(@RequestParam String appMail) {

        return service.findUserByAppMail(appMail);
    }

    @PutMapping("updateUser")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @DeleteMapping("deleteUser")
    public void deleteUser(@RequestParam String appMail) {
        service.deleteUser(appMail);
    }
}
