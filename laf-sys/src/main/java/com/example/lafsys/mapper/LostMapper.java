package com.example.lafsys.mapper;

import com.example.lafsys.entity.Lost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liang
 * @since 2024-03-10
 */
public interface LostMapper extends BaseMapper<Lost> {

    Boolean giveBack(Integer lostId, Integer userId);
}
