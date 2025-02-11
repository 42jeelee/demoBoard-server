package kr.co.jeelee.demoboard.domain.category.controller;

import java.util.List;
import java.util.UUID;

import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryDetailResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping
	public List<CategoryResponse> getCategories(
		@PageableDefault(size = 5) Pageable pageable
	) {
		return categoryService.findAll(pageable);
	}

	@GetMapping(value = "/{id}")
	public CategoryDetailResponse getCategory(
		@PathVariable UUID id
	) {
		return categoryService.findById(id);
	}

	@GetMapping(value = "/search")
	public List<CategoryResponse> search(
			@RequestParam String query,
			@PageableDefault(size = 5) Pageable pageable
	) {
		return categoryService.search(query, pageable);
	}

	@PostMapping
	public CategoryDetailResponse create(
			@RequestBody String name
	) {
		return categoryService.create(name);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(
			@PathVariable UUID id
	) {
		categoryService.delete(id);
	}

}
