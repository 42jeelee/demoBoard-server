package kr.co.jeelee.demoboard.domain.category.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import kr.co.jeelee.demoboard.domain.category.entity.Category;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CategoryResponse (Long id, String name, Long postCount) {
	public static CategoryResponse from(Category category, Long postCount) {
		return new CategoryResponse(category.getId(), category.getName(), postCount);
	}

	public static CategoryResponse from(Category category) {
		return new CategoryResponse(category.getId(), category.getName(), null);
	}
}
