package kr.co.jeelee.demoboard.domain.category.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.jeelee.demoboard.domain.category.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
