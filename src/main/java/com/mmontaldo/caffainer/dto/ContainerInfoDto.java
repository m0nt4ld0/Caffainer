package com.mmontaldo.caffainer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ContainerInfoDto {
    private String id;
    private String name;
    private String image;
    private String state;
}
