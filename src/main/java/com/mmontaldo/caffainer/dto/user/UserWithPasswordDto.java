package com.mmontaldo.caffainer.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithPasswordDto {

    private Long id;
    private String username;
    private String password;

}
