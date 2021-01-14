package com.globant.microservicedemo.controller;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Controller
public class ConsumerControllerClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public void getAllHeaders()throws RestClientException, IOException {
        List<ServiceInstance> instances = discoveryClient.getInstances("MICROSERVICEDEMO-ZUUL-SERVICE");
        ServiceInstance serviceInstance = instances.get(0);

        String baseUrl = serviceInstance.getUri().toString();

        baseUrl = baseUrl + "/producer/allHeaders";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(baseUrl, HttpMethod.GET, buildBody(null), String.class);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("######################    ########### headers: "+ response.getBody());
    }

    public void getHeadersByName(List<String> headerNames){
        List<ServiceInstance> instances = discoveryClient.getInstances("MICROSERVICEDEMO-ZUUL-SERVICE");
        ServiceInstance serviceInstance = instances.get(0);

        String baseUrl = serviceInstance.getUri().toString();

        baseUrl = baseUrl + "/producer/headers";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(baseUrl, HttpMethod.POST, buildBody(headerNames), String.class);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        System.out.println("######################    ########### headersByName: "+ response.getBody());
    }

    private static HttpEntity<?> buildBody(List<String> headerNames) throws IOException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        return new HttpEntity<>(headerNames, requestHeaders);
    }

}
