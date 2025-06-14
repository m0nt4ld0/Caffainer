package com.mmontaldo.caffainer.service.docker;

import java.util.List;

import com.mmontaldo.caffainer.dto.docker.ContainerInfoDto;

public interface ContainerService {
    List<String> getRunningContainers();
    ContainerInfoDto inspectContainer(String containerId);
}
