package kr.co.jeelee.demoboard.domain.category.service;

import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryDetailResponse;
import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.domain.category.entity.Category;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.co.jeelee.demoboard.domain.category.dao.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public CategoryDetailResponse findById(UUID id) {
		return categoryRepository.findById(id)
				.map(CategoryDetailResponse::from)
				.orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
	}

	@Override
	@Transactional
	public CategoryDetailResponse create(String request) {
		Category category = Category.of(request);

		return CategoryDetailResponse.from(categoryRepository.save(category));
	}

	@Override
	@Transactional
	public void delete(UUID id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public List<CategoryResponse> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable).stream()
				.map(CategoryResponse::from)
				.toList();
	}

	@Override
	public List<CategoryResponse> search(String query, Pageable pageable) {
		return categoryRepository.searchCategoriesByName(query, pageable).stream()
				.map(CategoryResponse::from)
				.toList();
	}
}
