package com.inquistivecat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inquistivecat.commom.Result;
import com.inquistivecat.dto.DishDto;
import com.inquistivecat.entity.Category;
import com.inquistivecat.entity.Dish;
import com.inquistivecat.entity.DishFlavor;
import com.inquistivecat.service.CategoryService;
import com.inquistivecat.service.DishFlavorService;
import com.inquistivecat.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 菜品管理
 * @author hp
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 保存新增菜品及其对应口味信息
     * @param dishDto
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody DishDto dishDto) {
        dishService.saveWithFlavor(dishDto);
        String key = "dish_"+dishDto.getCategoryId()+"_"+dishDto.getStatus();
        redisTemplate.delete(key);
        return  Result.success("新增菜品成功");
    }


    /**
     * 菜品分页信息查询，用dto展示数据
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result<Page> page(int page,int pageSize,String name){
        Page<Dish> pageInfo = new Page(page,pageSize);
        Page<DishDto> pageDto = new Page<>();
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<Dish>();
        queryWrapper.like(name!=null,Dish::getName,name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo, queryWrapper);
        BeanUtils.copyProperties(pageInfo, pageDto,"records");
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list = records.stream().map((item)->{
            DishDto dishDto = new DishDto();
            Category category = categoryService.getById(item.getCategoryId());
            if(category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }

            BeanUtils.copyProperties(item, dishDto);
            return dishDto;
        }).collect(Collectors.toList());
        pageDto.setRecords(list);



        return Result.success(pageDto);
    }


    /**
     * 根据id获取菜品及其对应口味信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<DishDto> get(@PathVariable Long id){
        return Result.success(dishService.getByIdWithFlavor(id));
    }


    /**
     * 修改某菜品及其对应口味信息
     * @param dishDto
     * @return
     */
    @PutMapping
    public Result<String> update(@RequestBody DishDto dishDto){

        dishService.updateWithFlavor(dishDto);
        String key = "dish_"+dishDto.getCategoryId()+"_"+dishDto.getStatus();
        redisTemplate.delete(key);
        return  Result.success("修改菜品成功");

    }

    /**
     * 根据指定条件来查询菜品信息
     * 一般是根据某个分类来查对应所有的菜品以及口味信息
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public Result<List<DishDto>> list(Dish dish){
        List<DishDto> listDto  = null;
        //动态构造key
        String key = "dish_"+dish.getCategoryId()+"_"+dish.getStatus();
        listDto = (List<DishDto>) redisTemplate.opsForValue().get(key);
        if(listDto!=null){
            return Result.success(listDto);
        }
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId()!=null,Dish::getCategoryId,dish.getCategoryId());
        queryWrapper.eq(Dish::getStatus,1);
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        List<Dish> list = dishService.list(queryWrapper);
        listDto = list.stream().map((item) -> {
            Long id = item.getId();
            LambdaQueryWrapper<DishFlavor> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(DishFlavor::getDishId,id);
            List<DishFlavor> flavors = dishFlavorService.list(wrapper);
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item,dishDto);
            dishDto.setFlavors(flavors);
            return dishDto;
        }).collect(Collectors.toList());
        redisTemplate.opsForValue().set(key,listDto,60, TimeUnit.MINUTES);
        return Result.success(listDto);
}

    /**
     * (批量)删除指定菜品及其对应口味信息
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam List<Long> ids){
        return Result.error("接口还未完成");
    }

    /**
     * (批量)修改菜品售卖状态
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public Result<String> updateStatus(@PathVariable int status,@RequestParam("ids") List<Long> ids){
        return Result.error("接口还未完成");
    }















}
