package com.ruin.springbootsecurity.dao;

import lombok.Data;

/**
 * @author ruin
 * @date 2019/12/9-17:11
 */
@Data
public class PermissionDto {
    private String id;
    private String code;
    private String description;
    private String url;
}
