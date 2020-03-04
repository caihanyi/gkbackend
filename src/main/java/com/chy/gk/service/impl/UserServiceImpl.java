package com.chy.gk.service.impl;

import com.chy.gk.model.uesr.User;
import com.chy.gk.repository.UserRepository;
import com.chy.gk.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Override
    public User getUserByName(String name) {
        return userRepository.getUserByUserName(name);
    }

    @Override
    public User getUserByPhoneNum(String phoneNum) {
        return userRepository.getUserByPhoneNum(phoneNum);
    }

    @Override
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }
}
