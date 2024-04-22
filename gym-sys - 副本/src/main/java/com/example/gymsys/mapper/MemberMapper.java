package com.example.gymsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gymsys.entity.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;
//@Mapper
public interface MemberMapper extends BaseMapper<Member> {
//    @Select("SELECT * from sys_member")
//    List<Member> findAll();
//
//    @Insert("INSERT INTO sys_member(member_id,member_password,member_name,member_gender,member_age,member_height,member_weight,member_phone,card_time,card_class,card_next_class) VALUES(#{member_id},#{member_password},#{member_name}," +
//            " #{member_gender},#{member_age},#{member_height},#{member_weight},#{member_phone},#{card_time},#{card_class},#{card_next_class})")
//    int insert(Member member);
//
//    int update(Member member);
//    @Delete("delete from sys_member where member_id = #{member_id}")
//    int deleteById(@Param("member_id") Integer member_id);
//
//    @Select("select * from sys_member where member_name like #{part_member_name} and card_time >= #{startDate} and card_time <= #{endDate} limit #{pageNum}, #{pageSize}")
//    List<Member> selectPage(Integer pageNum, Integer pageSize,String part_member_name,String startDate,String endDate);
//
//    @Select("select count(*) from sys_member where member_name like concat('%',#{part_member_name},'%') and card_time >= #{startDate} and card_time <= #{endDate}")
//    Integer selectTotal(String part_member_name,String startDate,String endDate);
}
