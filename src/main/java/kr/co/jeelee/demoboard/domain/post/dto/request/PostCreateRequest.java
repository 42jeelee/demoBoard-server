package kr.co.jeelee.demoboard.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PostCreateRequest(
		@NotBlank(message = "Name cannot be blank.") String title,
		@NotNull(message = "Author id cannot be NULL.") UUID authorId,
		@NotNull(message = "Category cannot be NULL.") UUID categoryId,
		String content
) {}
