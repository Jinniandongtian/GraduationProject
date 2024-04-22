package com.example.gymsys.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gymsys.entity.Member;
import com.example.gymsys.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService extends ServiceImpl<MemberMapper, Member> {


    public boolean saveMember(Member member){
        if(member.getMemberId() == null){
            System.out.println(member);
            save(member); //mybatis-plus提供的方法,表示插入数据
            member.setMemberPassword(member.getMemberId().toString());
            return updateById(member);
        }else {
            return updateById(member);
        }
//        return saveOrUpdate(member); //报错
    }
//    @Autowired
//    private MemberMapper memberMapper;
//
//    // 新增或更新
//    public int save(Member member){
//        if(member.getMember_id() == null) {  //没有id表示是新增
//
//            return memberMapper.insert(member);
//        }else { //否则为更新
//
//            return memberMapper.update(member);
//        }
//
//    }
}
