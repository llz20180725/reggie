package com.study.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.boke.dto.DishDto;
import com.study.boke.entity.Dish;
import com.study.boke.entity.Orders;


public interface OrdersService extends IService<Orders> {
        public void submit(Orders orders);
}
