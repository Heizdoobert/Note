package org.notebasement.note.repository;

import org.notebasement.note.entity.EntryAccess;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntryAccessRepository {
    boolean exitsByEntryId(String entryId);

    Optional<EntryAccess> findByEntryIdAndCategoryId(String entryId, String categoryId);
}
