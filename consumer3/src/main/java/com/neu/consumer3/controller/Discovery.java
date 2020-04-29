package com.neu.consumer3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Discovery {

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public String discovery() {
        StringBuilder sb = new StringBuilder();
        List<String> list = client.getServices();
        sb.append("**********").append(list).append("*********");
        sb.append("<br>");
        List<ServiceInstance> srvList = client.getInstances("provider");
        for (ServiceInstance element : srvList) {
            sb.append(element.getServiceId());
            sb.append('\t');
            sb.append(element.getUri());
            sb.append("<br>");
        }
        return sb.toString();
    }
}
