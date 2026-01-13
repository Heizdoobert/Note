package org.notebasement.note.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.notebasement.note.dto.request.CommentCreationRequest;
import org.notebasement.note.dto.request.CommentUpdateRequest;
import org.notebasement.note.dto.response.CommentResponse;
import org.notebasement.note.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toComment(CommentCreationRequest req);

    CommentResponse toCommentResponse(Comment comment);

    void updateComment(Comment comment, @MappingTarget CommentUpdateRequest commentResponse);
}
