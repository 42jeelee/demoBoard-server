package kr.co.jeelee.demoboard.domain.category.service;

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
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public List<CategoryResponse> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable).stream()
				.map(CategoryResponse::from)
				.toList();
	}

	@Override
	public CategoryResponse findById(UUID id) {
		return categoryRepository.findById(id)
				.map(CategoryResponse::from)
				.orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
	}

	@Override
	@Transactional
	public CategoryResponse create(String request) {
		Category category = Category.of(request);
		return CategoryResponse.from(categoryRepository.save(category));
	}

	@Override
	@Transactional
	public CategoryResponse update(UUID id, String request) {
		return null;
	}

	@Override
	@Transactional
	public void delete(UUID id) {
		categoryRepository.deleteById(id);
	}
}
