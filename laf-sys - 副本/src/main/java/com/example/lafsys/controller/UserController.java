package com.example.lafsys.controller;

import com.example.lafsys.entity.User;
impo  com.example.lafsys.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    @GetMapping
    public String test(){
        return "ok";
    }

    @PostMapping
    public Boolean save( @RequestBody User user){
        return userService.saveOrUpdate(user);
    }

}
