package com.smhrd.projectweb.controller.api.v1;

import com.smhrd.projectweb.entity.response.api.v1.IndexResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Index {

    @GetMapping({"", "/"})
    public IndexResponse index() {
        return new IndexResponse("Hello, World!");
    }
}
