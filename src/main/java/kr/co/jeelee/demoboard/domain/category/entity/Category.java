package kr.co.jeelee.demoboard.domain.category.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.co.jeelee.demoboard.domain.relationship.postCategory.entity.PostCategory;
import kr.co.jeelee.demoboard.global.entity.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter
public class Category extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "b", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PostCategory> postCategories;

	private Category(String name) {
		this.name = name;
		this.postCategories = new ArrayList<>();
	}

	public static Category of(String name) {
		return new Category(name);
	}
}
