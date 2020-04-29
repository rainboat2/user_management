package com.neu.provider3.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.neu.provider3.bean.TbUser;
import com.neu.provider3.dao.TbUserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final TbUserMapper userMapper;

    public UserController(TbUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private String isSuccess(Integer flag){
        return (flag != 0)? "success" : "failed";
    }

    @RequestMapping("/addUser")
    @HystrixCommand(fallbackMethod = "addUserWhenError")
    public Map<String, Object> addUser(@RequestParam("id") Integer id, @RequestParam("loginName") String loginName,
                                       @RequestParam("username") String username, @RequestParam("password") String pwd){
        TbUser tbUser = new TbUser(id, loginName, username, pwd);
        Integer flag = userMapper.insert(tbUser);
        Map<String, Object> rs = new HashMap<>();
        rs.put("state", isSuccess(flag));
        rs.put("user data which has added to database", tbUser);
        rs.put("solver", "provider1");
        return rs;
    }

    public Map<String, Object> addUserWhenError(@RequestParam("id") Integer id, @RequestParam("loginName") String loginName,
                                                @RequestParam("username") String username, @RequestParam("password") String pwd){
        TbUser tbUser = new TbUser(id, loginName, username, pwd);
        Map<String, Object> rs = new HashMap<>();
        rs.put("data", tbUser);
        rs.put("state", "some thing went wrong when add user to database");
        rs.put("solver", "provider1");
        return rs;
    }

    @RequestMapping("/removeUser")
    @HystrixCommand(fallbackMethod = "removeUserWhenError")
    public Map<String, Object> removeUser(@RequestParam("id") Integer id){
        if (id == -1) throw new RuntimeException();
        Integer flag = userMapper.deleteByPrimaryKey(id);
        Map<String, Object> rs = new HashMap<>();
        rs.put("state", isSuccess(flag));
        rs.put("the user id which is deleted", id);
        rs.put("solver", "provider1");
        return rs;
    }

    public Map<String, Object> removeUserWhenError(@RequestParam("id") Integer id){
        Map<String, Object> rs = new HashMap<>();
        rs.put("state", "some thing went wrong when deleted user");
        rs.put("the user's id which is deleted", id);
        rs.put("solver", "provider1");
        return rs;
    }

    @RequestMapping("/update")
    @HystrixCommand(fallbackMethod = "updateWhenError")
    public Map<String, Object> update(@RequestParam("id") Integer id, @RequestParam("loginName") String loginName,
                                      @RequestParam("username") String username, @RequestParam("password") String pwd){
        if (id == -1) throw new RuntimeException();
        TbUser tbUser = new TbUser(id, loginName, username, pwd);
        Integer flag = userMapper.updateByPrimaryKey(tbUser);
        Map<String, Object> rs = new HashMap<>();
        rs.put("state", isSuccess(flag));
        rs.put("user's data after update", tbUser);
        rs.put("solver", "provider1");
        return rs;
    }

    public Map<String, Object> updateWhenError(@RequestParam("id") Integer id, @RequestParam("loginName") String loginName,
                                               @RequestParam("username") String username, @RequestParam("password") String pwd){
        TbUser tbUser = new TbUser(id, loginName, username, pwd);
        Map<String, Object> rs = new HashMap<>();
        rs.put("state", "some thing went wrong when update user data");
        rs.put("user data after update", tbUser);
        rs.put("solver", "provider1");
        return rs;
    }

    @RequestMapping("/select")
    @HystrixCommand(fallbackMethod = "selectWhenError")
    public Map<String, Object> select(@RequestParam("id") Integer id, @RequestParam("loginName") String loginName,
                                      @RequestParam("username") String username){
        if (id == -11111)
            throw new RuntimeException();
        if (id == -1) id = null;
        TbUser tbUser = new TbUser(id, loginName, username, null);
        Map<String, Object> rs = new HashMap<>();
        rs.put("query data", tbUser);
        List<TbUser> users = userMapper.selectBy(tbUser);
        rs.put("query result", users);
        rs.put("state", "success");
        rs.put("solver", "provider1");
        return rs;
    }

    public Map<String, Object> selectWhenError(@RequestParam("id") Integer id, @RequestParam("loginName") String loginName,
                                               @RequestParam("username") String username){
        TbUser tbUser = new TbUser(id, loginName, username, null);
        Map<String, Object> rs = new HashMap<>();
        rs.put("query", tbUser);
        rs.put("state", "some thing went wrong when search user data from database");
        rs.put("solver", "provider1");
        return rs;
    }
}
