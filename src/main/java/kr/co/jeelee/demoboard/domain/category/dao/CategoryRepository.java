package kr.co.jeelee.demoboard.domain.category.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.jeelee.demoboard.domain.category.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	@Query(value = "SELECT COUNT(p) FROM POST p WHERE p.category.categoryId = :id")
	Long countPostByCategoryId(Long id);
}
