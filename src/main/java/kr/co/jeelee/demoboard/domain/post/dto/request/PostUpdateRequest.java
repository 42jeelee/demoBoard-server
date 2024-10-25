package kr.co.jeelee.demoboard.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PostUpdateRequest(
        @NotBlank(message = "Title cannot be blank.") String title,
        @NotBlank(message = "Password cannot be blank.") String password,
        String content
) {}
