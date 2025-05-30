package com.mmontaldo.caffainer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmontaldo.caffainer.service.impl.ContainerServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/container")
@AllArgsConstructor
public class ContainerController {
    private final ContainerServiceImpl containerServiceImpl;

    @GetMapping("/list")
    public String getList() {
        return "Hola Mundo";
    }

    @GetMapping("/listRunningContainers")
    public List<String> listRunningContainers() {
        return containerServiceImpl.getRunningContainers();
    }

    @GetMapping("/{id}/inspect")
    public String getDetails(@PathVariable Integer id) {
        return "Hola Mundo";
    }
    
    @GetMapping("/{id}/status")
    public String getStatus(@PathVariable Integer id) {
        return "Hola Mundo";
    }
    
}
