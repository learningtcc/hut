package com.hut.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jared on 2016/12/10.
 */

@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping
    public String index(){
        return "index";
    }
}
