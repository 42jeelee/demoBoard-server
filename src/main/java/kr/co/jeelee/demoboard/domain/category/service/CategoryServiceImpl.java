package kr.co.jeelee.demoboard.domain.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.jeelee.demoboard.domain.category.dao.CategoryRepository;
import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Override
	public List<CategoryResponse> findAll() {
		return categoryRepository.findAll().stream()
			.map(categoryEntity -> {
				Long postCount = countPostByCategoryId(categoryEntity.getCategoryId());
				return CategoryResponse.of(categoryEntity, postCount);
			}).toList();
	}

	@Override
	public CategoryResponse findById(Long id) {
		return categoryRepository.findById(id)
			.map(categoryEntity -> {
				Long postCount = countPostByCategoryId(categoryEntity.getCategoryId());
				return CategoryResponse.of(categoryEntity, postCount);
			})
			.orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
	}

	@Override
	public Long countPostByCategoryId(Long id) {
		return categoryRepository.countPostByCategoryId(id);
	}
}
