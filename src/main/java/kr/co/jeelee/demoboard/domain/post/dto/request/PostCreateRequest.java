package kr.co.jeelee.demoboard.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record PostCreateRequest(
		@NotBlank(message = "Name cannot be blank.") String title,
		@NotNull(message = "Author id cannot be NULL.") UUID authorId,
		@Size(min = 1, message = "Category must be at least one.") List<UUID> categories,
		String content
) {}
