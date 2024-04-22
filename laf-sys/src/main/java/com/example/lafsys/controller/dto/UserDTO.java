package com.example.lafsys.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    private String password;
    private String name;
    private String token;
    private String role;
//    private List<Menu> menus;
}
