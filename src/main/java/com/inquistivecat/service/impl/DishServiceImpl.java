package com.inquistivecat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inquistivecat.dto.DishDto;
import com.inquistivecat.entity.Dish;
import com.inquistivecat.entity.DishFlavor;
import com.inquistivecat.mapper.DishMapper;
import com.inquistivecat.service.DishFlavorService;
import com.inquistivecat.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hp
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService flavorService;
    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        this.save(dishDto);
        Long id = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        for (DishFlavor flavor : flavors) {
            flavor.setDishId(id);
        }
        flavorService.saveBatch(flavors);

    }
    /**
     * 根据id返回菜品信息和对应口味信息
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        Dish dish = this.getById(id);
        DishDto result = new DishDto();
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,id);
        List<DishFlavor> list = flavorService.list(queryWrapper);
        BeanUtils.copyProperties(dish,result);
        result.setFlavors(list);

        return result;
    }

    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        this.updateById(dishDto);
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        flavorService.remove(queryWrapper);
        List<DishFlavor> flavors = dishDto.getFlavors();
        for (DishFlavor flavor : flavors) {
            flavor.setDishId(dishDto.getId());
        }
        flavorService.saveBatch(flavors);


    }
}
