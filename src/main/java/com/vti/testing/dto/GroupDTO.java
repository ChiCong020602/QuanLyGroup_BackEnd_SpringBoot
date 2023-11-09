package com.vti.testing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.testing.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class GroupDTO {
    private int id;
    private String groupName;
    private int member;
    private String creatorUserName;
//    private User creator;
    @JsonFormat(pattern = "dd-MM-YYYY")
    private Date date;

//    @Data
//    @NoArgsConstructor
//    public static class User{
//        private int userId;
//        private String userName;
//    }
}
