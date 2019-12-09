package com.ruin.springbootsecurity.dao;

import com.ruin.springbootsecurity.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ruin
 * @date 2019/12/9-10:57
 */

@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserDto getUserByUsername(String username){
        String sql="select * from t_user where username=?";
        List<UserDto> list= jdbcTemplate.query(sql,new Object[]{username},
                new BeanPropertyRowMapper<>(UserDto.class));
        if(list==null&&list.size()<=0){
            return null;
        }
        return list.get(0);
    }

    //        根据用户的ID查询权限
    public List<String> findPermissionByUserId(String userId){
        String sql="SELECT\n" +
                "\t*\n" +
                "FROM\n" +
                "\tt_permission\n" +
                "WHERE\n" +
                "\tid IN (\n" +
                "\t\tSELECT\n" +
                "\t\t\tpermission_id\n" +
                "\t\tFROM\n" +
                "\t\t\tt_role_permission\n" +
                "\t\tWHERE\n" +
                "\t\t\trole_id IN (\n" +
                "\t\t\t\tSELECT\n" +
                "\t\t\t\t\trole_id\n" +
                "\t\t\t\tFROM\n" +
                "\t\t\t\t\tt_user_role\n" +
                "\t\t\t\tWHERE\n" +
                "\t\t\t\t\tuser_id = ?\n" +
                "\t\t\t)\n" +
                "\t)";
        List<PermissionDto> list = jdbcTemplate.query(sql, new Object[]{userId},
                new BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> permissions=new ArrayList<>();
        list.forEach(c ->permissions.add(c.getCode()));
        return  permissions;
    }
}
