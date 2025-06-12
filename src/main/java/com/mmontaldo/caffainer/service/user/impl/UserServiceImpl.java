package com.mmontaldo.caffainer.service.user.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mmontaldo.caffainer.dto.user.UserDto;
import com.mmontaldo.caffainer.dto.user.UserWithPasswordDto;
import com.mmontaldo.caffainer.entity.UserEntity;
import com.mmontaldo.caffainer.repository.security.UserRepository;
import com.mmontaldo.caffainer.service.user.UserService;

@Service
public record UserServiceImpl (
    UserRepository userRepository,
    ModelMapper modelMapper
) implements UserService {

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
        .map(user -> modelMapper.map(user, UserDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserWithPasswordDto user) {
        UserEntity ue = modelMapper.map(user, UserEntity.class);
        userRepository.save(ue);
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserByUsername(String username) {
        return modelMapper.map(userRepository.findByUsername(username), 
        UserDto.class);
    }

    public UserDto getUserById(Long id) {
        return modelMapper.map(userRepository.findById(id), 
        UserDto.class);
    }
}
