package com.gssg.gssgbe.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @PostMapping("/test")
    public void test() {
        System.out.println("system out");
        log.info("### log info");
        throw new RuntimeException("test!!");
    }
}
