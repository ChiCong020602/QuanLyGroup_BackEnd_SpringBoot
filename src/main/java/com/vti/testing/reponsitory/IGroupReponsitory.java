package com.vti.testing.reponsitory;


import com.vti.testing.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupReponsitory extends JpaRepository<Group, Integer> {
    Group findByGroupName(String name);
}
