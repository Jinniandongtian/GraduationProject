package com.example.lafsys.controller;

import com.example.lafsys.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.lafsys.service.ICheckService;
import com.example.lafsys.entity.Check;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liang
 * @since 2024-03-15
 */
@RestController
@RequestMapping("/check")
    public class CheckController {

    @Resource
    private ICheckService checkService;

    //新增或更新
    @PostMapping
    public boolean save(@RequestBody  Check  check){
            return  checkService.saveOrUpdate(check);
            }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
            return checkService.removeById(id);
    }

    // 批量删除接口
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){  //@PathVariable从URI中提取参数值
            return checkService.removeByIds(ids);
    }
    @GetMapping
    public List<Check> findAll() {
            return checkService.list();
    }

    @GetMapping("/{id}")
    public Check findOne(@PathVariable Integer id) {
            return checkService.getById(id);
    }

    @GetMapping("/page")
    public Page<Check> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize) {
            QueryWrapper<Check> queryWrapper = new QueryWrapper<>();
            return checkService.page(new Page<>(pageNum, pageSize),queryWrapper);
            }
    }

