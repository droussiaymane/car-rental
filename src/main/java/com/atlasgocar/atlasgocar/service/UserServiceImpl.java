package com.atlasgocar.atlasgocar.service;


import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.User;
import com.atlasgocar.atlasgocar.repository.UserRepository;
import com.atlasgocar.atlasgocar.sharedDto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean addUser(UserDto userDto, Agence agence) {
        boolean existsByEmail = userRepository.existsByEmail(userDto.getEmail());
        if(!existsByEmail){
            ModelMapper modelMapper=new ModelMapper();
            User NewuserDto = modelMapper.map(userDto, User.class);
            NewuserDto.setActive(true);
            NewuserDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            NewuserDto.setAgence(agence);
            userRepository.save(NewuserDto);
            return true;
        }
        return false;


    }

    @Override
    public List<UserDto> getAllAgent() {
        List<User> allUsers = userRepository.findAll();
        ModelMapper modelMapper=new ModelMapper();
        List<UserDto> userDtos=new ArrayList<UserDto>();
        for(User user:allUsers){
            UserDto userDto = modelMapper.map(user, UserDto.class);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public void deletedAgentById(Long id) {
        Boolean existsById = userRepository.existsById(id);

        if(!existsById){
            throw new Error("L'utilisateur est introuvable");
        }
        else{
            userRepository.deleteById(id);
        }

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}
