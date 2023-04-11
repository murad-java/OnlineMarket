package com.murad.operationsservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @GetMapping("/index")
    public String getIndex(){
        return "/page-listing-grid.html";
    }
}
