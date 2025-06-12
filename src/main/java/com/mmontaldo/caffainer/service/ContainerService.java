package com.mmontaldo.caffainer.service;

import java.util.List;

import com.mmontaldo.caffainer.dto.ContainerInfoDto;

public interface ContainerService {
    List<String> getRunningContainers();
    ContainerInfoDto inspectContainer(String containerId);
}
