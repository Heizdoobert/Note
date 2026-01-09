package org.notebasement.identity.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.notebasement.common.exception.AppException;
import org.notebasement.common.exception.ErrorCode;
import org.notebasement.identity.dto.request.UserCreationRequest;
import org.notebasement.identity.dto.request.UserUpdateRequest;
import org.notebasement.identity.dto.response.UserResponse;
import org.notebasement.identity.entity.User;
import org.notebasement.identity.mapper.UserMapper;
import org.notebasement.identity.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    /// CRUD
    /// Create
    public UserResponse createUser(UserCreationRequest req){
        if(userRepository.existsByEmail(req.getEmail())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(req);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    /// Read
    public List<UserResponse> getUsers(){
        return userRepository
                .findAll()
                .stream().map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUser(String userId){
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    /// Update
    public UserResponse updateUser(String id, UserUpdateRequest req){
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, req);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    /// Delete
    public void deleteUser(String userId){
        if(!userRepository.existsById(userId)){
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(userId);
    }
}
