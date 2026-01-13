package org.notebasement.note.repository;

import org.notebasement.note.entity.EntryAccess;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntryRepository {
    boolean exitsByEntryIdAndCategoryId(String entryId, String categoryId);

    Optional<EntryAccess> findByEntryIdAndCategoryId(String entryId, String categoryId);
}
