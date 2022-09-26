package com.inquistivecat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.inquistivecat.entity.Category;

/**
 * @author hp
 */
public interface CategoryService extends IService<Category> {
    /**
     * 移除指定id分类信息
     * @param id
     *
     */
    void remove(Long id);
}
