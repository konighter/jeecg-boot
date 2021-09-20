package com.hanzhan.tech.workbench.controller;

import com.hanzhan.tech.workbench.candidate.entity.Candidate;
import com.hanzhan.tech.workbench.candidate.entity.CandidateVisithistory;
import com.hanzhan.tech.workbench.candidate.service.ICandidateService;
import com.hanzhan.tech.workbench.candidate.service.ICandidateVisithistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/testNew")
public class TestNewController {

    @Autowired
    private ICandidateService candidateService;
    @Autowired
    private ICandidateVisithistoryService candidateVisithistoryService;
    @PostConstruct
    public void post(){
        log.error("TestNewController init");
    }


    @RequestMapping("/index")
    public String index(@RequestParam("id") String id) {
//        Candidate c = candidateService.getById(id);
//
        List<CandidateVisithistory> h = candidateVisithistoryService.selectByMainId(id);
        log.info("id={}, candidate={}", id, h);
        return "index";
    }
}
