package com.example.lafsys.mapper;

import com.example.lafsys.entity.Files;
import com.example.lafsys.entity.Find;
import com.example.lafsys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liang
 * @since 2024-03-10
 */
public interface UserMapper extends BaseMapper<User> {

    void claim(Integer id, Integer findId);


    void giveBack(Integer id, Integer lostId);
}
