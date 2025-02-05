package kr.co.jeelee.demoboard.domain.category.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.jeelee.demoboard.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(value = "SELECT c.id, COUNT(p.id) FROM Category c LEFT JOIN Post p ON c.id = p.id GROUP BY c.id")
	List<Object[]> countPostByCategoryId();

}
