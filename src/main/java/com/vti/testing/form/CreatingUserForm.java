package com.vti.testing.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CreatingUserForm {
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
