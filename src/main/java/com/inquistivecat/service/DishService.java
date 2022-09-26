package com.inquistivecat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.inquistivecat.dto.DishDto;
import com.inquistivecat.entity.Dish;

/**
 * @author hp
 */
public interface DishService extends IService<Dish> {
    /**
     * 新增菜品及其对应口味，操作两张表
     * @param dishdto
     */
    public void saveWithFlavor(DishDto dishdto);

    /**
     * 根据id返回菜品信息和对应口味信息
     * @param id
     * @return
     */
    public DishDto getByIdWithFlavor(Long id);

    /**
     * 修改菜品及其对应口味，操作两张表
     * @param dishdto
     */
    void updateWithFlavor(DishDto dishdto);
}
