package com.study.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.boke.entity.Category;
import org.springframework.stereotype.Service;


public interface CategoryService extends IService<Category> {
    void remove(long id);
}
