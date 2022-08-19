package com.study.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.boke.dto.SetmealDto;
import com.study.boke.entity.Setmeal;
import com.study.boke.entity.SetmealDish;
import com.study.boke.exception.ReggieException;
import com.study.boke.exception.ReggieExceptionEnum;
import com.study.boke.mapper.SetmealMapper;
import com.study.boke.service.SetmealDishService;
import com.study.boke.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    SetmealDishService setmealDishService;

    @Override
    public void saveWithDish(SetmealDto setmealDto){
        this.save(setmealDto);

        List<SetmealDish> dishList = setmealDto.getSetmealDishes();
        dishList = dishList.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(dishList);
    }

    @Override
    public void deleteWithDish(List<Long> ids){
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);
        int count = this.count(queryWrapper);
        if (count > 0){
            throw new ReggieException(ReggieExceptionEnum.DELETE_ERROR);
        }
        this.removeByIds(ids);
        LambdaQueryWrapper<SetmealDish> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(wrapper);
    }
}
