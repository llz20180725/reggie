package com.study.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.boke.entity.Category;
import com.study.boke.entity.Dish;
import com.study.boke.entity.Setmeal;
import com.study.boke.exception.ReggieException;
import com.study.boke.exception.ReggieExceptionEnum;
import com.study.boke.mapper.CategoryMapper;
import com.study.boke.service.CategoryService;
import com.study.boke.service.DishService;
import com.study.boke.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    DishService dishService;

    @Autowired
    SetmealService setmealService;
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void remove(long categoryId){
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dish::getCategoryId,categoryId);
        int count = dishService.count(queryWrapper);
        if (count>0){
            throw new ReggieException(ReggieExceptionEnum.DELETE_ERROR);
        }

        LambdaQueryWrapper<Setmeal> mealQueryWrapper = new LambdaQueryWrapper<>();
        mealQueryWrapper.eq(Setmeal::getCategoryId,categoryId);
        count = dishService.count(queryWrapper);
        if (count>0){
            throw new ReggieException(ReggieExceptionEnum.DELETE_ERROR);
        }
        categoryMapper.deleteById(categoryId);

    }
}
