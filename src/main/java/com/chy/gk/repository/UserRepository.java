package com.chy.gk.repository;

import com.chy.gk.model.uesr.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor {
    public User getUserByUserName(String userName);

    public User getUserByPhoneNum(String PhoneNum);
}
