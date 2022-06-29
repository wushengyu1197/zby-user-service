package com.shopping.service.UserInfo;

import com.shopping.model.UserInfo;

import java.util.List;

public interface UserInfoService {
    UserInfo findById(Long id);

    UserInfo findByOne(UserInfo userInfo);

    int insertOne(UserInfo userInfo);

    int updateOne(UserInfo userInfo);

    int DeleteOne(int id);

    List<UserInfo> findAll();
}
