package com.inquistivecat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.inquistivecat.dto.SetmealDto;
import com.inquistivecat.entity.Setmeal;

import java.util.List;

/**
 * @author hp
 */
public interface SetmealService extends IService<Setmeal> {


    /**
     * 保存套餐及其对应菜品信息
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 移除指定id套餐以及对应关系
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
