package com.gure.cinab.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Request received: Welcome");
    }


}
