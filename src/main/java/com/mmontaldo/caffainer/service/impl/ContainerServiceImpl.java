package com.mmontaldo.caffainer.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ListContainersCmd;
import com.github.dockerjava.api.model.Container;
import com.mmontaldo.caffainer.service.ContainerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContainerServiceImpl implements ContainerService {
    
    private final DockerClient dockerClient;

    public List<String> getRunningContainers() {
        ListContainersCmd listContainersCmd = dockerClient.listContainersCmd().withShowAll(true);
        List<Container> containers = listContainersCmd.exec();

        return containers.stream()
                .map(container -> container.getId().substring(0, 12) + " - " + String.join(", ", container.getNames()))
                .collect(Collectors.toList());
    }
}
