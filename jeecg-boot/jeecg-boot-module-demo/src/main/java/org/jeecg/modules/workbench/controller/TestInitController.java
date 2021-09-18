package org.jeecg.modules.workbench.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/testinit")
public class TestInitController {

    @PostConstruct
    public void post() {
        System.out.printf("init");
    }

    @ResponseBody
    @RequestMapping("/index")
    public String index() {
        return "hello test";
    }

}
