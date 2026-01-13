package org.notebasement.note.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.notebasement.common.exception.AppException;
import org.notebasement.common.exception.ErrorCode;
import org.notebasement.note.dto.request.CommentCreationRequest;
import org.notebasement.note.dto.response.CommentResponse;
import org.notebasement.note.entity.Comment;
import org.notebasement.note.mapper.CommentMapper;
import org.notebasement.note.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {
    CommentRepository commentRepository;
    CommentMapper commentMapper;

    public CommentResponse toCommentResponse(CommentCreationRequest req) {
        Comment comment = commentMapper.toComment(req);
        return commentMapper.toCommentResponse(commentRepository.save(comment));
    }

    /// dang dung o day doi ohat trien tiep

    public CommentResponse createComment(CommentCreationRequest req) {
        if (commentRepository.existsByCategoryId(req.getCommentId())) {
            throw new AppException(ErrorCode.COMMENT_NOT_FOUND);
        }
        Comment comment = commentMapper.toComment(req);

        comment.setCommentId(req.getCommentId());

        return commentMapper.toCommentResponse(commentRepository.save(comment));
    }

    public List<CommentResponse> getComments() {
        return commentRepository.findAll().stream()
                .map(commentMapper::toCommentResponse)
                .collect(Collectors.toList());
    }

    public CommentResponse getComment(String commentId) {
        return commentMapper.toCommentResponse(commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND)));
    }

    public CommentResponse updateComment(String commentId, CommentCreationRequest req) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        commentMapper.updateComment(comment, req);
        return commentMapper.toCommentResponse(commentRepository.save(comment));
    }

    public void deleteComment(String commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new AppException(ErrorCode.COMMENT_NOT_FOUND);
        }

        commentRepository.deleteById(commentId);
    }
}
