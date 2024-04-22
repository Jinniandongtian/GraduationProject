package com.example.lafsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.lafsys.entity.UserFind;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserLostMapper extends BaseMapper<UserFind> {
    @Select("select lost_id from sys_user_lost where user_id = #{userId}")
    List<Integer> selectByUserId(@Param("userId") Integer userId);

    @Delete("delete from sys_user_lost where lost_id = #{lostId}")
    Boolean deleteByLostId(@Param("lostId") Integer lostId);

}
