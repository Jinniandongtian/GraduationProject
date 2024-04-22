package com.example.gymsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gymsys.entity.Member;
import com.example.gymsys.mapper.MemberMapper;
import com.example.gymsys.service.MemberService;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberService memberService;
    //查询所有数据
    @GetMapping()
    public List<Member> findAll(){
        return memberService.list();
    }

    @PostMapping
    public boolean save(@RequestBody Member member){
        System.out.println("mapper层:"+member);
        return memberService.saveMember(member);
    }
    @DeleteMapping("/{member_id}")
    public boolean delete(@PathVariable Integer member_id){  //@PathVariable从URI中提取参数值
        return memberService.removeById(member_id);
    }

    // 批量删除接口
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> member_ids){  //@PathVariable从URI中提取参数值
        return memberService.removeByIds(member_ids);
    }
    //分页查询 mybatis的方式
    //接口路径:/member/page/pageNum=1&pageSize=10
    //@RequestParam接受 "?pageNum=1&pageSize=10" 然后提取参数
//    @GetMapping("/page")
//    public  Map<String, Object> findPage(@RequestParam Integer pageNum,
//                                                    @RequestParam Integer pageSize,
//                                                    @RequestParam String part_member_name,
//                                                    @RequestParam String startDate,
//                                                    @RequestParam String endDate
//                                         ){
//        pageNum = (pageNum - 1) * pageSize;
//        part_member_name = "%" + part_member_name + "%";
//        List<Member> data = memberMapper.selectPage(pageNum,pageSize,part_member_name,startDate,endDate);
//        Integer total = memberMapper.selectTotal(part_member_name,startDate,endDate);
//        Map<String,Object> res = new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
//        return  res;
//    }
    // 分页查询 - mybatis-plus的方式
    @GetMapping("/page")
    public  IPage<Member> findPage(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam String part_member_name,
                                         @RequestParam String startDate,
                                         @RequestParam String endDate
    ){
        IPage<Member> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        if(!"".equals(part_member_name)){
            queryWrapper.like("member_name",part_member_name);
        }
        queryWrapper.between("card_time", startDate, endDate);
        return memberService.page(page,queryWrapper);

    }
}
