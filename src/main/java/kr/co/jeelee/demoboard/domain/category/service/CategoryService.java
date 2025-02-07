package kr.co.jeelee.demoboard.domain.category.service;

import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.global.service.EntityService;

public interface CategoryService
		extends EntityService<
			String, String,
			CategoryResponse, CategoryResponse
		> {
}
