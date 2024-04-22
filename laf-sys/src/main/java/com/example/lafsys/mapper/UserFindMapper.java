package com.example.lafsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.lafsys.entity.UserFind;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserFindMapper extends BaseMapper<UserFind> {
    @Select("select find_id from sys_user_find where user_id = #{userId}")
    List<Integer> selectByUserId(@Param("userId") Integer userId);

    @Delete("delete from sys_user_find where find_id = #{findId}")
    Boolean deleteByFindId(@Param("findId") Integer findId);
}
