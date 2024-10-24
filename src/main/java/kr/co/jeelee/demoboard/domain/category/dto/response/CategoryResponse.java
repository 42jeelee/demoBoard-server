package kr.co.jeelee.demoboard.domain.category.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import kr.co.jeelee.demoboard.domain.category.entity.CategoryEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse {
	private final Long id;
	private final String name;
	private final Long postCount;

	public static CategoryResponse of(CategoryEntity category, Long postCount) {
		return new CategoryResponse(category.getId(), category.getName(), postCount);
	}
}
