package org.notebasement.identity.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String email;
    String fullName;
    String password;
    String confirmPassword;
    String layoutConfig;
    String profileData;
    LocalDate updatedAt;
}
