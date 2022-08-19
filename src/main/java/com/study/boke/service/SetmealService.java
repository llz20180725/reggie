package com.study.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.boke.dto.SetmealDto;
import com.study.boke.entity.Dish;
import com.study.boke.entity.Setmeal;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);

    void deleteWithDish(List<Long> ids);
}
