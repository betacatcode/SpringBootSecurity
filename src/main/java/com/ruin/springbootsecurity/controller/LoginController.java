package com.ruin.springbootsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ruin
 * @date 2019/12/8-10:10
 */
@RestController
public class LoginController {
    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess(){
//        提示具体的用户名称
        return getUserName()+"登陆成功";
    }

    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    public String r1(){
        return "访问资源r1";
    }

    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(){
        return "访问资源r2";
    }

//    获取当前用户信息
    private String getUserName(){
//       当前用户认证通过的用户身份
        String username=null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(principal==null){
            username="匿名";
        }
//
        if(principal instanceof UserDetails){
            UserDetails userDetails= (UserDetails) principal;
            username=userDetails.getUsername();
        }else {
            username=principal.toString();
        }
        return username;
    }
}

