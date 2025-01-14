package com.gure.cinab.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @GetMapping(value = "${api.prefix}/", produces = "application/json")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Request received: Welcome");
    }


}
