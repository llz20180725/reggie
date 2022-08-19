package com.study.boke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.boke.entity.OrderDetail;
import com.study.boke.entity.Orders;
import com.study.boke.mapper.OrderDetailMapper;
import com.study.boke.mapper.OrdersMapper;
import com.study.boke.service.OrderDetailService;
import com.study.boke.service.OrdersService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {


}
