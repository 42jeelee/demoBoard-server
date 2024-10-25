package kr.co.jeelee.demoboard.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentCreateRequest(
        @NotBlank(message = "author can't be Blank.") String author,
        @NotNull(message = "content can't be Null.") String content
) {}
