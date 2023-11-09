package com.vti.testing.reponsitory;

import com.vti.testing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserReponsitory extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    User findByEmail(String email);
}
