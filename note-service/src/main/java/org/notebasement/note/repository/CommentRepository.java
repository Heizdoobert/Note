package org.notebasement.note.repository;

import org.notebasement.note.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    Boolean existsByCategoryId(String categoryId);

    Optional<Comment> findByCategoryId(String categoryId);
}
