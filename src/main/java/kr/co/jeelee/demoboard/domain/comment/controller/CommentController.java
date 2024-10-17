package kr.co.jeelee.demoboard.domain.comment.controller;

import jakarta.validation.Valid;
import kr.co.jeelee.demoboard.domain.comment.dto.request.CommentCreateRequest;
import kr.co.jeelee.demoboard.domain.comment.dto.response.CommentResponse;
import kr.co.jeelee.demoboard.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
@Validated
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentResponse> getComments(
            @PathVariable Long postId,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        return commentService.findAllByPostId(postId, pageable);
    }

    @PostMapping
    public CommentResponse addComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentCreateRequest request
    ) {
        return commentService.createByPostId(postId, request);
    }

    @DeleteMapping(path = "/{commentId}")
    public void deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        commentService.deleteCommentById(postId, commentId);
    }

}
