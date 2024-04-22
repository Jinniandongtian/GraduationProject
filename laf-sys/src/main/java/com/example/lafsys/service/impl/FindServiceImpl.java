package com.example.lafsys.service.impl;

import com.example.lafsys.entity.Find;
import com.example.lafsys.mapper.FindMapper;
import com.example.lafsys.service.IFindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liang
 * @since 2024-03-13
 */
@Service
public class FindServiceImpl extends ServiceImpl<FindMapper, Find> implements IFindService {

    @Resource
    FindMapper findMapper;
    @Override
    public Boolean claim(Integer findId, Integer userId) {
        return findMapper.claim(findId,userId);
    }
}
