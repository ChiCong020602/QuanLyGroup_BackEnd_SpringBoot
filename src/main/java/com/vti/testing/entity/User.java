package com.vti.testing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`User`")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "`username`", length = 50, nullable = false, unique = true)
    private String userName;

    @Column(name = "`email`", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "firstName", length = 50, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 50, nullable = false)
    private String lastName;

    @Column(name = "`password`", length = 800, nullable = false)
    private String password;

    @Column(name = "`status`")
    private int status;

    @OneToMany(mappedBy = "creator")
    private List<Group> groups;
}
