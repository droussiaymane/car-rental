package com.atlasgocar.atlasgocar.service;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.User;
import com.atlasgocar.atlasgocar.sharedDto.UserDto;

import java.util.List;

public interface UserService {
    public boolean addUser(UserDto userDto, Agence agence);

    public List<UserDto> getAllAgent();

    public void deletedAgentById(Long id);

    public User findByEmail(String email);
}
