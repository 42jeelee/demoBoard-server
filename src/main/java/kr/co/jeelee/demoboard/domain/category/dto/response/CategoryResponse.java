package kr.co.jeelee.demoboard.domain.category.dto.response;

import kr.co.jeelee.demoboard.domain.category.entity.CategoryEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryResponse {
	private final Long id;
	private final String name;
	private final Long postCount;

	public static CategoryResponse of(CategoryEntity category, Long postCount) {
		return new CategoryResponse(category.getCategoryId(), category.getName(), postCount);
	}
}
