package com.neu.consumer2.controller;

import com.neu.consumer2.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @RequestMapping("/addUser")
    public Map<String, Object> addUser(@RequestParam("id") Integer id, @RequestParam("loginName") String loginName,
                                       @RequestParam("username") String username, @RequestParam("password") String pwd){
        Map<String, Object> rs = userServices.addUser(id, loginName, username, pwd);
        rs.put("solver", rs.getOrDefault("solver", "") + " consumer1");
        return rs;
    }

    @RequestMapping("/removeUser")
    public Map<String, Object> removeUser(@RequestParam("id") Integer id){
        if (id == null) id = -1;
        return userServices.removeUser(id);
    }

    @RequestMapping("/update")
    public Map<String, Object> update(@RequestParam("id") Integer id, @RequestParam("loginName") String loginName,
                                      @RequestParam("username") String username, @RequestParam("password") String pwd){
        if (id == null) id = -1;
        Map<String, Object> rs = userServices.update(id, loginName, username, pwd);
        rs.put("solver", rs.getOrDefault("solver", "") + " consumer1");
        return rs;
    }

    @RequestMapping("/select")
    public Map<String, Object> select(@RequestParam("id") Integer id, @RequestParam("loginName") String loginName,
                                      @RequestParam("username") String username){
        if (id == null) id = -1;
        Map<String, Object> rs = userServices.select(id, loginName, username);
        rs.put("solver", rs.getOrDefault("solver", "") + " consumer1");
        return rs;
    }
}
