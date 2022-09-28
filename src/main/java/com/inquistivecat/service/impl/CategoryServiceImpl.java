package com.inquistivecat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inquistivecat.commom.CustomException;
import com.inquistivecat.entity.Category;
import com.inquistivecat.entity.Dish;
import com.inquistivecat.entity.Setmeal;
import com.inquistivecat.mapper.CategoryMapper;
import com.inquistivecat.service.CategoryService;
import com.inquistivecat.service.DishService;
import com.inquistivecat.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hp
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    @Override
    public void removeCategory(Long id) {
        LambdaQueryWrapper<Dish>  lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.count(lambdaQueryWrapper);
        if(count1>0){
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(queryWrapper);
        if(count2>0) {
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        super.removeById(id);

















    }
}
