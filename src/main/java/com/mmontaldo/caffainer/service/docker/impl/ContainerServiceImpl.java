package com.mmontaldo.caffainer.service.docker.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerCmd;
import com.github.dockerjava.api.command.ListContainersCmd;
import com.github.dockerjava.api.model.Container;
import com.mmontaldo.caffainer.dto.docker.ContainerInfoDto;
import com.mmontaldo.caffainer.service.docker.ContainerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContainerServiceImpl implements ContainerService {
    
    private final DockerClient dockerClient;
    private final ModelMapper modelMapper;

    public List<String> getRunningContainers() {
        ListContainersCmd listContainersCmd = dockerClient.listContainersCmd().withShowAll(false);
        List<Container> containers = listContainersCmd.exec();

        return containers.stream()
                .map(container -> container.getId().substring(0, 12) + " - " + String.join(", ", container.getNames()))
                .collect(Collectors.toList());
    }

    public ContainerInfoDto inspectContainer(String containerId) {
        InspectContainerCmd inspectContainerCmd = dockerClient.inspectContainerCmd(containerId);
        return modelMapper.map(inspectContainerCmd.exec(),ContainerInfoDto.class);
    }
}
