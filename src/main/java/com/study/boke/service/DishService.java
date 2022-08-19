package com.study.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.boke.dto.DishDto;
import com.study.boke.entity.Category;
import com.study.boke.entity.Dish;
import org.springframework.stereotype.Service;


public interface DishService extends IService<Dish> {
    void DishAndFlavor(DishDto dishDto);

    DishDto getByIdAndFlavor(long id);

    void updateDishWithFlavor(DishDto dishDto);
}
