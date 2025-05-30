package com.mmontaldo.caffainer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerController {
    
    @GetMapping("/containers/{id}/inspect")
    public String getProcessInfo(@PathVariable Integer id) {
        return "Hola Mundo";
    }
}
