package org.notebasement.identity.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String email;
    String fullName;
    String password;
    String profileData;
    String layoutConfig;
    LocalDate createdAt;
    LocalDate updatedAt;
}
