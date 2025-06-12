package com.mmontaldo.caffainer.dto;

import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerInfoDto {
    private String id;
    private String name;
    private String image;
    private String state;
    private String[] args;
    private String created;
    private String driver;
    private String execDriver;
    private String hostnamePath;
    private String hostsPath;
    private String logPath;
    private Integer sizeRootFs;
    private Integer sizeRw;
    private String imageId;
    private String mountLabel;
    private Integer restartCount;
    private String path;
    private String processLabel;
    private String resolvConfPath;
    private List<String> execIds;
    private String platfor;
}