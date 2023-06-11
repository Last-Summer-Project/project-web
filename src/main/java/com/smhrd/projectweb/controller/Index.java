package com.smhrd.projectweb.controller;

import com.smhrd.projectweb.shared.ResultWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Index {

    @GetMapping({"", "/"})
    public ResultWrapper<Void> index() {
        return ResultWrapper.ok("안녕, 세계!");
    }
}
