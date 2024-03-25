package com.wiki.controller;

import com.wiki.dto.AppUserDto;
import com.wiki.model.AppUser;
import com.wiki.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/AppUser")
@AllArgsConstructor
public class AppUserController {
    private final AppUserService service;
    @GetMapping
    public List<AppUser> findAllAppUser(){return service.findAllAppUser();}

    @PostMapping("saveAppUser")
    public AppUser saveAppUser(@RequestBody AppUser appUser) {
        return service.saveAppUser(appUser);
    }

    @GetMapping("findByAppMail")
    public AppUser findByAppMail(@RequestParam String appMail) {

        return service.findByAppMail(appMail);
    }

    @PutMapping("updateAppUser")
    public AppUser updateAppUser(@RequestBody AppUser appUser) {
        return service.updateAppUser(appUser);
    }

    @DeleteMapping("deleteAppUser")
    public void deleteAppUser(@RequestParam String appMail) {
        service.deleteAppUser(appMail);
    }
}
