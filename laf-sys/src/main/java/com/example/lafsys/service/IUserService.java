package com.example.lafsys.service;

import com.example.lafsys.entity.Files;
import com.example.lafsys.entity.Find;
import com.example.lafsys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.lafsys.controller.dto.UserDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liang
 * @since 2024-03-10
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    void claim(Integer id, Integer findId);


    void giveBack(Integer id, Integer lostId);
}
