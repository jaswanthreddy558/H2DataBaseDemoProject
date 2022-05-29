package com.taintedmonk.h2learning.h2demoapp.controller;

import com.taintedmonk.h2learning.h2demoapp.service.MockAccountGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mockaccount")
public class MockAccountController {

    @Autowired
    private MockAccountGenerateService mockAccountGenerateService;

    @GetMapping("/generateDummy")
    public void generateDummy(){
        mockAccountGenerateService.generateAccounts();
    }
}
