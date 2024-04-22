package com.example.lafsys.service;

import com.example.lafsys.entity.Lost;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liang
 * @since 2024-03-10
 */
public interface ILostService extends IService<Lost> {

    Boolean giveBack(Integer lostId, Integer userId);
}
