package kr.co.jeelee.demoboard.domain.category.entity;

import jakarta.persistence.*;
import kr.co.jeelee.demoboard.domain.post.entity.PostEntity;
import kr.co.jeelee.demoboard.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "CATEGORY")
@NoArgsConstructor
@Getter
public class CategoryEntity extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	private List<PostEntity> posts;
}
