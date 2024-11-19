package kr.co.jeelee.demoboard.domain.category.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import kr.co.jeelee.demoboard.domain.category.entity.CategoryEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CategoryResponse (Long id, String name, Long postCount) {
	public static CategoryResponse of(CategoryEntity category, Long postCount) {
		return new CategoryResponse(category.getId(), category.getName(), postCount);
	}
}
