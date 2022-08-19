package com.study.boke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.boke.entity.Dish;
import com.study.boke.entity.Setmeal;
import com.study.boke.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {
}
