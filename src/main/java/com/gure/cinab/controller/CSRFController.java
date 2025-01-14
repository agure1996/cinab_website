package com.gure.cinab.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("${api.prefix}/csrf")
public class CSRFController {

    // Endpoint to generate and retrieve CSRF token, to be used for testing endpoints while having a csrf token
    //without csrf token spring-security will not authorise letting me hit post/delete/put endpoints
    @GetMapping("/token")
    public CsrfToken getToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
