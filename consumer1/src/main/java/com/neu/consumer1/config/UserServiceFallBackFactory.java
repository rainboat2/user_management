package com.neu.consumer1.config;

import com.neu.consumer1.bean.TbUser;
import com.neu.consumer1.services.UserServices;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserServices> {

    @Override
    public UserServices create(Throwable throwable) {
        return new UserServices() {
            @Override
            public Map<String, Object> addUser(Integer id, String loginName, String username, String pwd) {
                Map<String, Object> rs = new HashMap<>();
                rs.put("state", "add user service is currently not available");
                rs.put("user's data which failed to add", new TbUser(id, loginName, username, pwd));
                rs.put("solver", rs.getOrDefault("solver", "") + " consumer1");
                return rs;
            }

            @Override
            public Map<String, Object> removeUser(Integer id) {
                Map<String, Object> rs = new HashMap<>();
                rs.put("state", "remove user service is currently not available");
                rs.put("id of the user who failed to remove", id);
                rs.put("solver", rs.getOrDefault("solver", "") + " consumer1");
                return rs;
            }

            @Override
            public Map<String, Object> update(Integer id, String loginName, String username, String pwd) {
                Map<String, Object> rs = new HashMap<>();
                rs.put("state", "update user service is currently not available");
                rs.put("user's data which failed to update", new TbUser(id, loginName, username, pwd));
                rs.put("solver", rs.getOrDefault("solver", "") + " consumer1");
                return rs;
            }

            @Override
            public Map<String, Object> select(Integer id, String loginName, String username) {
                Map<String, Object> rs = new HashMap<>();
                rs.put("state", "search user service is currently not available");
                rs.put("data used to search user", new TbUser(id, loginName, username, null));
                rs.put("solver", rs.getOrDefault("solver", "") + " consumer1");
                return rs;
            }
        };
    }
}
