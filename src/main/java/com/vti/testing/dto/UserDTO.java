package com.vti.testing.dto;

import com.vti.testing.entity.Group;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private int userId;
    private String userName;
//    private List<Group> groups;
    private String email;
    private String firstName;
    private String lastName;

    @Data
    @NoArgsConstructor
    public static class Group {
        private int id;
        private String groupName;
    }
}
