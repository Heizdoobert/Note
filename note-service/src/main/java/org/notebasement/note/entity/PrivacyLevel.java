package org.notebasement.note.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

public enum PrivacyLevel {
    PUBLIC,
    PRIVATE,
    PROTECTED1,
    PROTECTED2,
}