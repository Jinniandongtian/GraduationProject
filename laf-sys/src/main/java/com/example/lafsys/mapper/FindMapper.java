package com.example.lafsys.mapper;

import com.example.lafsys.entity.Find;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liang
 * @since 2024-03-13
 */
public interface FindMapper extends BaseMapper<Find> {

    Boolean claim(Integer findId, Integer userId);
}
