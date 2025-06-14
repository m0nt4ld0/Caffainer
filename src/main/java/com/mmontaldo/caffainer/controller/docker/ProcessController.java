package com.mmontaldo.caffainer.controller.docker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
public class ProcessController {
    //ToDo
    @GetMapping("/list")
    public String getList() {
        return "Hola Mundo";
    }
    //ToDo
    @GetMapping("/{id}/info")
    public String getDetails(@PathVariable Integer id) {
        return "Hola Mundo";
    }
    //ToDo
    @GetMapping("/{id}/status")
    public String getStatus(@PathVariable Integer id) {
        return "Hola Mundo";
    }
    
}
