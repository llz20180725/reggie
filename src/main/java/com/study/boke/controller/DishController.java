package com.study.boke.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.boke.common.R;
import com.study.boke.dto.DishDto;
import com.study.boke.entity.Dish;
import com.study.boke.entity.DishFlavor;
import com.study.boke.service.CategoryService;
import com.study.boke.service.DishFlavorService;
import com.study.boke.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {
    @Autowired
    DishService dishService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    DishFlavorService dishFlavorService;

    @PostMapping
    public void save(@RequestBody DishDto dishDto){
        dishService.DishAndFlavor(dishDto);
    }

    @GetMapping("/page")
    public R<Page> page(String name,int pageNum,int pageSize){
        Page<Dish> pageinfo = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name!=null,Dish::getName,name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageinfo,queryWrapper);

        Page<DishDto> pageDishdto = new Page<>();

        BeanUtils.copyProperties(pageinfo,pageDishdto,"records");

        List<Dish> records = pageinfo.getRecords();
        List<DishDto> dtoList = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            String categoryName = categoryService.getById(item.getCategoryId()).getName();
            dishDto.setCategoryName(categoryName);
            return dishDto;
        }).collect(Collectors.toList());
        pageDishdto.setRecords(dtoList);
        return R.success(pageDishdto);
    }

    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable long id){
        DishDto dishDto = dishService.getByIdAndFlavor(id);
        return R.success(dishDto);
    }

    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        dishService.updateDishWithFlavor(dishDto);
        return R.success("修改成功！");
    }

    @GetMapping("/list")
    public R<List<DishDto>> list(@RequestBody Dish dish){
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId()!=null,Dish::getCategoryId,dish.getCategoryId());
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        queryWrapper.eq(Dish::getStatus,1);
        List<Dish> dishList = dishService.list(queryWrapper);
        List<DishDto> dishDtoList = new ArrayList<>();
        List<DishDto> collect = dishList.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            LambdaQueryWrapper<DishFlavor> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(item.getId() != null, DishFlavor::getDishId, item.getId());
            List<DishFlavor> flavorList = dishFlavorService.list(queryWrapper1);
            dishDto.setFlavors(flavorList);
            dishDtoList.add(dishDto);
            return dishDto;
        }).collect(Collectors.toList());

        return R.success(dishDtoList);
    }
}
