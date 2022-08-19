package com.study.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.boke.dto.DishDto;
import com.study.boke.entity.AddressBook;
import com.study.boke.entity.Dish;
import com.study.boke.entity.DishFlavor;
import com.study.boke.mapper.AddressBookMapper;
import com.study.boke.mapper.DishMapper;
import com.study.boke.service.AddressBookService;
import com.study.boke.service.DishFlavorService;
import com.study.boke.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {


}
