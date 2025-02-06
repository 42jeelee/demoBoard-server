package kr.co.jeelee.demoboard.domain.category.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import kr.co.jeelee.demoboard.domain.category.dao.CategoryRepository;
import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.domain.post.service.PostService;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final PostService postService;

	@Override
	public List<CategoryResponse> findAll(final boolean isPostCount) {
		return categoryRepository.findAll().stream()
			.map(CategoryResponse::from).toList();
	}

	@Override
	public CategoryResponse findById(final UUID id, final boolean isPostCount) {
		return categoryRepository.findById(id)
			.map(CategoryResponse::from)
			.orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
	}

}
