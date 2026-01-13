package org.notebasement.note.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String entryId;
    String userId;
    String categoryId;
    String title;
    String content;
    PrivacyLevel privacyLevel;
    Boolean allowComments;
    LocalDate createdAt;
}
