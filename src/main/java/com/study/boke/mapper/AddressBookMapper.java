package com.study.boke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.boke.entity.AddressBook;
import com.study.boke.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
