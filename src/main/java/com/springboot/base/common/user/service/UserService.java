package com.springboot.base.common.user.service;


import com.springboot.base.common.user.entity.User;

public interface UserService {
    /**
     * 根据token获取User信息
     * @param token
     * @return User
     */
    public User getUser(String token);
}
