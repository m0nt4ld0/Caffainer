package com.mmontaldo.caffainer.service.user;

import java.util.List;

import com.mmontaldo.caffainer.dto.user.UserDto;
import com.mmontaldo.caffainer.dto.user.UserWithPasswordDto;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserByUsername(String username);
    UserDto createUser(UserWithPasswordDto user);
    UserDto getUserById(Long id);
}
