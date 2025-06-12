package com.mmontaldo.caffainer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mmontaldo.caffainer.dto.ContainerInfoDto;
import com.mmontaldo.caffainer.service.impl.ContainerServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/container")
@AllArgsConstructor
public class ContainerController {
    private final ContainerServiceImpl containerServiceImpl;

    @GetMapping("/ok")
    public String getList() {
        return "ok";
    }

    @GetMapping("/listRunningContainers")
    public List<String> listRunningContainers() {
        return containerServiceImpl.getRunningContainers();
    }

    @GetMapping("/{containerId}/inspect")
    public ContainerInfoDto getDetails(@PathVariable String containerId) {
        return containerServiceImpl.inspectContainer(containerId);
    }
}
