package org.notebasement.identity.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String email;
    String fullName;
    @Size(min = 1, max = 100, message = "Your password at least 4 characters!!!")
    String password;
    String profileData;
    String layoutConfig;
    LocalDate createdAt;
    LocalDate updatedAt;
}
