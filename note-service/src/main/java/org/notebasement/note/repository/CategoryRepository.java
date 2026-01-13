package org.notebasement.note.repository;

import org.notebasement.note.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean exitsByCategoryId(String categoryId);

    Optional<Category> findByCategoryId(String categoryId);
}
