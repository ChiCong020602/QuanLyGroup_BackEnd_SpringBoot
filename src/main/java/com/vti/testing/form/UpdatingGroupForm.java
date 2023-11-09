package com.vti.testing.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatingGroupForm {
    private int id;
    private String groupName;
    private int member;
}
