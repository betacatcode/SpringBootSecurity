package com.ruin.springbootsecurity.service;

import com.ruin.springbootsecurity.dao.UserDao;
import com.ruin.springbootsecurity.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ruin
 * @date 2019/12/8-16:43
 */

@Service
public class SpringDataUserDetailsService implements UserDetailsService{

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto=userDao.getUserByUsername(username);
        if(userDto==null){
//            用户查不到 返回null
            return null;
        }

        List<String> permissions = userDao.findPermissionByUserId(userDto.getId());

//        将permissions转成数组
        String[] permissionArray=new String[permissions.size()];
        permissions.toArray(permissionArray);
        UserDetails userDetails=User.withUsername(userDto.getUsername())
                .password(userDto.getPassword()).authorities(permissionArray).build();
        return userDetails;
    }


}
