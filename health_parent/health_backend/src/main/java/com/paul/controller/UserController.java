package com.paul.controller;

import com.paul.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/findUsername")
    public Result findUsername(){
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new Result(true,"1",user.getUsername());
        }catch (Exception e){
            return new Result(false,"2");
        }
    }
}
