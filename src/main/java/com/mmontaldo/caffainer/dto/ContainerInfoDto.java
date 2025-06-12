package com.mmontaldo.caffainer.dto;

import java.util.List;

public record ContainerInfoDto(
    String id,
    String name,
    String image,
    String state,
    String[] args,
    String created,
    String driver,
    String execDriver,
    String hostnamePath,
    String hostsPath,
    String logPath,
    Integer sizeRootFs,
    Integer sizeRw,
    String imageId,
    String mountLabel,
    Integer restartCount,
    String path,
    String processLabel,
    String resolvConfPath,
    List<String> execIds,
    String platform
) {}