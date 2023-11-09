package com.vti.testing.form;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePassForm {
    private String oldPass;
    private String newPass;
}
