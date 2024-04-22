package com.example.lafsys.service.impl;

import com.example.lafsys.entity.Lost;
import com.example.lafsys.mapper.LostMapper;
import com.example.lafsys.service.ILostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liang
 * @since 2024-03-10
 */
@Service
public class LostServiceImpl extends ServiceImpl<LostMapper, Lost> implements ILostService {

    @Resource
    LostMapper lostMapper;
    @Override
    public Boolean giveBack(Integer lostId, Integer userId) {
        return lostMapper.giveBack(lostId,userId);
    }
}
