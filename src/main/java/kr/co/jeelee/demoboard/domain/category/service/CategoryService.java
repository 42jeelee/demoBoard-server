package kr.co.jeelee.demoboard.domain.category.service;

import java.util.List;
import java.util.UUID;

import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;

public interface CategoryService {

	List<CategoryResponse> findAll(boolean isPostCount);

	CategoryResponse findById(UUID id, boolean isPostCount);

}
