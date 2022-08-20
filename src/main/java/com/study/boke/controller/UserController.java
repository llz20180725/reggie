package com.study.boke.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.study.boke.common.R;
import com.study.boke.entity.User;
import com.study.boke.service.UserService;
import com.study.boke.utils.SMSUtils;
import com.study.boke.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user,HttpSession session){
        String phone = user.getPhone();
        if (!StringUtils.isEmpty(phone)){
            String code = ValidateCodeUtils.generateValidateCode4String(4);
            System.out.println(code);
            //SMSUtils.sendMessage("[安大吴奇隆]","",phone,code);
            //session.setAttribute(phone,code);
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.success("短信发送成功");
        }
        return R.success("短信发送失败");
    }

    @PostMapping("/login")
    public R<User> sendMsg(@RequestBody Map map, HttpSession session){
         String phone = map.get("phone").toString();
         String code = map.get("code").toString();
        //Object codeInSession = session.getAttribute(phone);
        Object codeInSession = redisTemplate.opsForValue().get(phone);
        if (codeInSession!=null && code.equals(codeInSession)){
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if (user == null){
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            redisTemplate.delete(phone);
            return R.success(user);
        }
        return R.error("登录失败");
    }
}
