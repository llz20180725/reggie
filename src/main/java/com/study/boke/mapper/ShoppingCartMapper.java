package com.study.boke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.boke.entity.Dish;
import com.study.boke.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
