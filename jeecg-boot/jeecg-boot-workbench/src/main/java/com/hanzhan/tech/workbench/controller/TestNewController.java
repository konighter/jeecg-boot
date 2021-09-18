package com.hanzhan.tech.workbench.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/testNew")
public class TestNewController {
    @PostConstruct
    public void post(){
        log.error("TestNewController init");
    }


    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
