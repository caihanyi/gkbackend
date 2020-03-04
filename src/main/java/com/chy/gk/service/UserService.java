package com.chy.gk.service;

import com.chy.gk.model.uesr.User;

public interface UserService {
    public User getUserByName(String name);
    public User getUserByPhoneNum(String phoneNum);
    public User addUser(User user);
}
