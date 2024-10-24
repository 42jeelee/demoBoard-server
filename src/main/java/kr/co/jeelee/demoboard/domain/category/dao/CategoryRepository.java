package kr.co.jeelee.demoboard.domain.category.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.jeelee.demoboard.domain.category.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	@Query(value = "SELECT c.id, COUNT(p.id) FROM CATEGORY c LEFT JOIN POST p ON c.id = p.category.id GROUP BY c.id")
	List<Object[]> countPostByCategoryId();

}
