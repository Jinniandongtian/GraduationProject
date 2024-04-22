package com.example.lafsys.controller;

import com.example.lafsys.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lafsys.mapper.UserFindMapper;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.lafsys.service.IFindService;
import com.example.lafsys.entity.Find;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liang
 * @since 2024-03-13
 */
@RestController
@RequestMapping("/find")
    public class FindController {

    @Resource
    private IFindService findService;

    @Resource
    private UserFindMapper userFindMapper;
    //新增或更新
    @PostMapping
    public boolean save(@RequestBody  Find  find){
            return  findService.saveOrUpdate(find);
            }

     //认领 更新sys_find表的is_claim和user_id属性
    @PostMapping("/{findId}/{userId}")
    public Boolean claim(@PathVariable Integer findId, @PathVariable Integer userId){
        return findService.claim(findId,userId);
    }
    // 删除sys_user_find表中的对应关系
    @DeleteMapping("/userFind/{findId}")
    public Boolean deleteByFindId(@PathVariable Integer findId){
        return userFindMapper.deleteByFindId(findId);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
            return findService.removeById(id);
    }

    // 批量删除接口
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){  //@PathVariable从URI中提取参数值
            return findService.removeByIds(ids);
    }
    @GetMapping
    public List<Find> findAll() {
            return findService.list();
    }

    @GetMapping("/{id}")
    public Find findOne(@PathVariable Integer id) {
            return findService.getById(id);
    }

    @GetMapping("/page")
    public Page<Find> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String name) {
            QueryWrapper<Find> queryWrapper = new QueryWrapper<>();
            if(!"".equals(name)){
            queryWrapper.like("find_name",name);
            }
            return findService.page(new Page<>(pageNum, pageSize),queryWrapper);
    }
}
