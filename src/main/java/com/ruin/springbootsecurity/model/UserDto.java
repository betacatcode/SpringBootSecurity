package com.ruin.springbootsecurity.model;

import lombok.Data;

/**
 * @author ruin
 * @date 2019/12/9-10:55
 */
@Data
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
}
