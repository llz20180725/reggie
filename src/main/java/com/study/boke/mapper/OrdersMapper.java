package com.study.boke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.boke.entity.Dish;
import com.study.boke.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
