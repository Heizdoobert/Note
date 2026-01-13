package org.notebasement.note.controller;

import org.notebasement.common.dto.ApiResponse;
import org.notebasement.note.dto.request.CommentCreationRequest;
import org.notebasement.note.dto.response.CommentResponse;
import org.notebasement.note.entity.Comment;
import org.notebasement.note.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    ApiResponse<CommentResponse> createComment(@RequestBody CommentCreationRequest req) {
        ApiResponse<CommentResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(commentService.createComment(req));
        return apiResponse;
    }

    @GetMapping
    List<CommentResponse> getComments() {
        return commentService.getComments();
    }

    @GetMapping("/{commentId}")
    CommentResponse getComment(@PathVariable String commentId) {
        return commentService.getComment(commentId);
    }

    @PutMapping("/{commentId}")
    CommentResponse updateComment(@PathVariable String commentId, @RequestBody CommentCreationRequest req) {
        return commentService.updateComment(commentId, req);
    }

    @DeleteMapping("/{commentId}")
    String deleteComment(@PathVariable String commentId) {
        commentService.deleteComment(commentId);
        return "comment deleted";
    }
}
