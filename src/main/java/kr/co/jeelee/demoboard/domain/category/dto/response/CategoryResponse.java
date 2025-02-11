package kr.co.jeelee.demoboard.domain.category.dto.response;

import kr.co.jeelee.demoboard.domain.category.entity.Category;

import java.util.UUID;

public record CategoryResponse (UUID id, String name, int postCount) {
	public static CategoryResponse from(Category category) {
		return new CategoryResponse(
				category.getId(),
				category.getName(),
				category.getPostCategories().size()
		);
	}
}
