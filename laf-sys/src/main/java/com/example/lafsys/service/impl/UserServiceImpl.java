package com.example.lafsys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lafsys.common.Constants;
import com.example.lafsys.entity.Files;
import com.example.lafsys.entity.Find;
import com.example.lafsys.entity.User;
import com.example.lafsys.controller.dto.UserDTO;
import com.example.lafsys.exception.ServiceException;
import com.example.lafsys.mapper.UserFindMapper;
import com.example.lafsys.mapper.UserMapper;
import com.example.lafsys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lafsys.utils.TokenUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liang
 * @since 2024-03-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Log LOG = Log.get();

    @Resource
    UserMapper userMapper;


    @Override
    public UserDTO login(UserDTO userDTO) {
        // 从数据库中找现有的数据
        User one = getUserInfo(userDTO);
        if(one != null){
            BeanUtil.copyProperties(one, userDTO, true);
            // 设置token
            String token =  TokenUtils.genToken(one.getId().toString(),one.getPassword());
            userDTO.setToken(token);

//            String role = one.getRole();   // ROLE_ADMIN
//            // 设置用户的菜单列表
//            List<Menu> roleMenus = getRoleMenus(role);
//            memberDTO.setMenus(roleMenus);
            return userDTO;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    @Override
    public void claim(Integer id, Integer findId) {
        userMapper.claim(id,findId);
    }

    @Override
    public void giveBack(Integer id, Integer lostId) {
        userMapper.giveBack(id,lostId);
    }


    private User getUserInfo(UserDTO userDTO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userDTO.getId());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); //从数据库查询用户信息
        } catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }
}
