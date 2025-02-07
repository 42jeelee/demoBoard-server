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
import java.util.UUID;

@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
@Validated
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentResponse> getComments(
            @PathVariable UUID postId,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        return commentService.findAllByParentId(postId, pageable);
    }

    @PostMapping
    public CommentResponse addComment(
            @PathVariable UUID postId,
            @Valid @RequestBody CommentCreateRequest request
    ) {
        return commentService.createByParentId(postId, request);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteComment(
            @PathVariable UUID postId,
            @PathVariable UUID id
    ) {
        commentService.delete(postId, id);
    }

}
