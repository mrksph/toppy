package com.mrksph.toppy.services;

import com.mrksph.toppy.dto.UserDTO;
import com.mrksph.toppy.exception.EntityNotFoundException;
import com.mrksph.toppy.exception.ValidationException;
import com.mrksph.toppy.model.UserEntity;
import com.mrksph.toppy.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntity registerUser(UserDTO req) {
        if (req.getEmail() == null) {
            throw new ValidationException("Email is required");
        }
        if (repository.existsByEmail(req.getEmail())) {
            throw new ValidationException("Email already exists.");
        }
        if (repository.existsByUsername(req.getUsername())) {
            throw new ValidationException("Username already exists.");
        }

        UserEntity user = modelMapper.map(req, UserEntity.class);

        user.setPassword(bCryptPasswordEncoder.encode(req.getPassword()));

        return repository.save(user);
    }

    public UserEntity loginUser(UserDTO req) {
        if (req.getUsername() == null) {
            throw new ValidationException("Username is required");
        }
        UserEntity userEntity = repository.findUserByUsername(req.getUsername()).orElseThrow(() -> new EntityNotFoundException());
        String reqPassword = req.getPassword();
        boolean isPasswordCorrect = userEntity.getPassword().equals(bCryptPasswordEncoder.encode(reqPassword));
        if(!isPasswordCorrect) {
            throw new ValidationException("");
        }
        return userEntity;
    }

    public UserEntity getUserByUsername(String username) {
        return repository.findUserByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(404, "User not found.", null));
    }
}
