package com.smhrd.projectweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping(value = "/", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String index() {
        return "안녕, 세계!";
    }
}
