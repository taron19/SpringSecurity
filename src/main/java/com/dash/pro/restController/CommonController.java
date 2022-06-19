package com.dash.pro.restController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dash")
@Slf4j
public class CommonController {

    @RequestMapping("/all/{name}")
    private String allAccess(@PathVariable("name") String name){
        log.info("int the all method");
        return "hello "+ name;
    }

    @GetMapping("/user")
    private String onlyUser(@RequestParam("name") String name){
        log.info("int the user method");
        return "hello "+name;
    }


    @GetMapping("/admin")
    private String onlyAdmin(@RequestParam("name") String name){
        log.info("int the admin method");
        return "hello "+name;
    }


}
