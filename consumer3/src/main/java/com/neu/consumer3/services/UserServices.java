package com.neu.consumer3.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "provider", fallbackFactory = com.neu.consumer3.config.UserServiceFallBackFactory.class)
@Service
public interface UserServices {

    @RequestMapping("/addUser")
    Map<String, Object> addUser(@RequestParam("id") Integer id, @RequestParam("loginName") String loginName,
                                @RequestParam("username") String username, @RequestParam("password") String pwd);

    @RequestMapping("/removeUser")
    Map<String, Object> removeUser(@RequestParam("id") Integer id);

    @RequestMapping("/update")
    Map<String, Object> update(@RequestParam("id") Integer id, @RequestParam("loginName") String loginName,
                               @RequestParam("username") String username, @RequestParam("password") String pwd);

    @RequestMapping("/select")
    Map<String, Object> select(@RequestParam("id") Integer id, @RequestParam("loginName") String loginName,
                               @RequestParam("username") String username);
}
