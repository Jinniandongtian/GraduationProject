package com.example.lafsys.service.impl;

import com.baomidou.mybatisplus.ex nsion.service.impl.ServiceImpl;
import com.example.lafsys.entity.User;
import com.example.lafsys.mapper.UserMapper;
import com.example.lafsys.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
