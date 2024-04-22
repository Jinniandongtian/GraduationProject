package com.example.lafsys.service;

import com.example.lafsys.entity.Find;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liang
 * @since 2024-03-13
 */
public interface IFindService extends IService<Find> {

    Boolean claim(Integer findId, Integer userId);
}
