package com.example.lafsys.controller;

import com.example.lafsys.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lafsys.mapper.UserLostMapper;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.lafsys.service.ILostService;
import com.example.lafsys.entity.Lost;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liang
 * @since 2024-03-10
 */
@RestController
@RequestMapping("/lost")
    public class LostController {

    @Resource
    private ILostService lostService;

    @Resource
    private UserLostMapper userLostMapper;

    //新增或更新
    @PostMapping
    public boolean save(@RequestBody  Lost  lost){
            return  lostService.saveOrUpdate(lost);
            }

    //归还 更新sys_lost表的is_return和user_id属性
    @PostMapping("/{lostId}/{userId}")
    public Boolean giveBack(@PathVariable Integer lostId, @PathVariable Integer userId){
        return lostService.giveBack(lostId,userId);
    }
    // 删除sys_user_lost表中的对应关系
    @DeleteMapping("/userLost/{lostId}")
    public Boolean deleteByLostId(@PathVariable Integer lostId){
        return userLostMapper.deleteByLostId(lostId);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
            return lostService.removeById(id);
    }

    // 批量删除接口
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){  //@PathVariable从URI中提取参数值
            return lostService.removeByIds(ids);
    }
    @GetMapping
    public List<Lost> findAll() {
            return lostService.list();
    }

    @GetMapping("/{id}")
    public Lost findOne(@PathVariable Integer id) {
            return lostService.getById(id);
    }

    @GetMapping("/page")
    public Page<Lost> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                               @RequestParam String name) {
            QueryWrapper<Lost> queryWrapper = new QueryWrapper<>();
            if(!"".equals(name)){
            queryWrapper.like("lost_name",name);
            }
            return lostService.page(new Page<>(pageNum, pageSize),queryWrapper);
            }
    }
