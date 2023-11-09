package com.vti.testing.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingGroupForm {
    private String groupName;
    private int member;
    private int creatorUserId;
}
