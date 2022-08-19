package com.study.boke.dto;

import com.study.boke.entity.Setmeal;
import com.study.boke.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
