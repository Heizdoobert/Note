package org.notebasement.identity.controller;

import jakarta.validation.Valid;
import org.notebasement.common.dto.ApiResponse;
import org.notebasement.identity.dto.request.UserCreationRequest;
import org.notebasement.identity.dto.request.UserUpdateRequest;
import org.notebasement.identity.dto.response.UserResponse;
import org.notebasement.identity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest req) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(req));
        return apiResponse;
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest req) {
        return userService.updateUser(userId, req);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "Deleted user successfully";
    }
}
