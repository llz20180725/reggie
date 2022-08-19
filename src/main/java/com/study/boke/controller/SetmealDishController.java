package com.study.boke.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.boke.common.R;
import com.study.boke.dto.SetmealDto;
import com.study.boke.entity.Setmeal;
import com.study.boke.entity.SetmealDish;
import com.study.boke.service.CategoryService;
import com.study.boke.service.SetmealDishService;
import com.study.boke.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealDishController {
    @Autowired
    SetmealService setmealService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SetmealDishService setmealDishService;

    @PostMapping
    public R<String> saveWithDish(@RequestBody SetmealDto setmealDto){
        setmealService.saveWithDish(setmealDto);

        return R.success("保存成功");
    }

    @GetMapping("/page")
    public R<Page> page(String name,int pageNum,int pageSize){
        Page<Setmeal> pageinfo = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name!=null,Setmeal::getName,name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageinfo,queryWrapper);
        Page<SetmealDto> pageDto = new Page<>();
        BeanUtils.copyProperties(pageinfo,pageDto,"records");
        List<Setmeal> records = pageinfo.getRecords();
        List<SetmealDto> list = null;
        list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);
            String categoryName = categoryService.getById(setmealDto.getCategoryId()).getName();
            setmealDto.setCategoryName(categoryName);
            return setmealDto;
        }).collect(Collectors.toList());
        pageDto.setRecords(list);
        return R.success(pageDto);
    }

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        setmealService.deleteWithDish(ids);
        return R.success("删除成功！");
    }

    @GetMapping
    public R<Setmeal> list(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId()!=null,Setmeal::getCategoryId,setmeal.getCategoryId());
        Setmeal serviceOne = setmealService.getOne(queryWrapper);
        return R.success(serviceOne);
    }
}
