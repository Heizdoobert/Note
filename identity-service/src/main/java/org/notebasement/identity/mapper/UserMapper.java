package org.notebasement.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.notebasement.identity.dto.request.UserCreationRequest;
import org.notebasement.identity.dto.request.UserUpdateRequest;
import org.notebasement.identity.dto.response.UserResponse;
import org.notebasement.identity.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @Mapping(source = "email", target = "email")
    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
