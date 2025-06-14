package com.mmontaldo.caffainer.dto.docker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InspectDto {

    private String id;
    private String name;
    private String image;
    private Map<String,String> state;
    private String[] args;
    private LocalDateTime created;
    private String driver;
    private String execDriver;
    private String hostnamePath;
    private String hostsPath;
    private String logPath;
    private String sizeRootFs;
    private String sizeRw;
    private String mountLabel;
    private Integer restartCount;
    private String path;
    private String processLabel;
    private String resolvConfPath;
    private String execIds;
    private String platfor;
}
