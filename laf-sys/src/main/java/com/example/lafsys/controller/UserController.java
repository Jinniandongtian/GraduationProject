package com.example.lafsys.controller;

import cn.hutool.core.util.StrUtil;
import com.example.lafsys.common.Constants;
import com.example.lafsys.common.Result;
import com.example.lafsys.entity.Files;
import com.example.lafsys.entity.Find;
import com.example.lafsys.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lafsys.controller.dto.UserDTO;
import com.example.lafsys.mapper.UserFindMapper;
import com.example.lafsys.mapper.UserLostMapper;
import com.example.lafsys.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lafsys.service.IUserService;
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
@RequestMapping("/user")
    public class UserController {

    @Resource
    private IUserService userService;

    @Resource
    UserFindMapper userFindMapper;

    @Resource
    UserLostMapper userLostMapper;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        Integer id = userDTO.getId();
        String password = userDTO.getPassword();
        String id1 = id.toString();
        if(StrUtil.isBlank(id1) || StrUtil.isBlank(password)){

            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return  Result.success(dto);
    }
    //新增或更新
    @PostMapping
    public boolean save(@RequestBody  User  user){
            return  userService.saveOrUpdate(user);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
            return userService.removeById(id);
    }

    // 批量删除接口
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){  //@PathVariable从URI中提取参数值
            return userService.removeByIds(ids);
    }
    @GetMapping
    public List<User> findAll() {
            return userService.list();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {
            return userService.getById(id);
    }

    @GetMapping("/page")
    public Page<User> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String name) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            if(!"".equals(name)){
                queryWrapper.like("name",name);
            }
            return userService.page(new Page<>(pageNum, pageSize),queryWrapper);
    }
    // 向sys_user_find表插入数据
    @PostMapping("/find/{id}/{findId}")
    public void claim(@PathVariable Integer id,@PathVariable Integer findId){
        userService.claim(id,findId);
    }

    @GetMapping("/find/{id}")
    public List<Integer> getUserFind(@PathVariable Integer id) {
        return userFindMapper.selectByUserId(id);
    }

    // 向sys_user_lost表插入数据
    @PostMapping("/lost/{id}/{lostId}")
    public void giveBack(@PathVariable Integer id,@PathVariable Integer lostId){
        userService.giveBack(id,lostId);
    }

    @GetMapping("/lost/{id}")
    public List<Integer> getUserLost(@PathVariable Integer id) {
        return userLostMapper.selectByUserId(id);
    }
}
