package com.shopping.controller;

import com.shopping.model.UserInfo;
import com.shopping.service.UserInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * zby个人原创
 *
 * @author zby
 * @date 2022/06/28 15:12
 **/
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("addNewUser")
    public Boolean addNewUser(UserInfo userInfo) {
        return userInfoService.insertOne(userInfo)>0;
    }

    @GetMapping("/findAllUser")
    public List<UserInfo> findAllUser(){
        return userInfoService.findAll();
    }

    @GetMapping("/findOne")
    public UserInfo findOne(Long id){

        return userInfoService.findById(id);
    }
}