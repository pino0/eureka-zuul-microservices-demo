package com.globant.microservicedemo.controller;

import com.globant.microservicedemo.utils.Constants;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class DemoController {

    @GetMapping(Constants.GET_ALL_HEADERS)
    public Map<String, List<String>> getAllHeaders(HttpServletRequest request){

        return getHeaderNames(request).stream()
                .collect(Collectors
                        .toMap(name->name, name->getHeaderValues(request, name)));
    }

    @PostMapping(Constants.GET_HEADER_BY_NAME)
    public Map<String, List<String>> getHeadersByName(HttpServletRequest request, @RequestBody List<String> headerNames){

        return getHeaderNames(request).stream()
                .filter(name -> headerNames.stream()
                        .anyMatch(filterName -> filterName.equals(name)))
                .collect(Collectors.toMap(name->name, name->getHeaderValues(request, name)));
    }

    //TODO: implement functional programming
    private static List<String> getHeaderValues( HttpServletRequest request, String headerName){
        List<String> values = new ArrayList<>();

        Enumeration<String> headerValues = request.getHeaders(headerName);
        while(headerValues != null && headerValues.hasMoreElements()){
            values.add(headerValues.nextElement());
        }

        return values;
    }

    //TODO: implement functional programming
    private static List<String> getHeaderNames( HttpServletRequest request){
        List<String> names = new ArrayList<>();
        String name;

        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames != null && headerNames.hasMoreElements()){
            name = (String)headerNames.nextElement();
            names.add(name);
        }

        return names;
    }

}
