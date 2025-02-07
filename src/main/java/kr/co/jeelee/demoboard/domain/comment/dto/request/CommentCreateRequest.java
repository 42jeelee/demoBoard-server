package kr.co.jeelee.demoboard.domain.comment.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CommentCreateRequest(
        @NotNull(message = "author can't be Null.") UUID authorId,
        @NotNull(message = "content can't be Null.") String content
) {}
