package com.shopping.service.UserInfo.impl;

import com.shopping.mapper.UserInfoMapper;
import com.shopping.model.UserInfo;
import com.shopping.service.UserInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public UserInfo findById(Long id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public UserInfo findByOne(UserInfo userInfo) {
        return null;
    }

    @Override
    public int insertOne(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    @Override
    public int updateOne(UserInfo userInfo) {
        return userInfoMapper.updateById(userInfo);
    }

    @Override
    public int DeleteOne(int id) {
        return userInfoMapper.deleteById(id);
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }
}
