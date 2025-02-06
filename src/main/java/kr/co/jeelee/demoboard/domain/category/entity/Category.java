package kr.co.jeelee.demoboard.domain.category.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.co.jeelee.demoboard.domain.post.entity.Post;
import kr.co.jeelee.demoboard.global.entity.BaseTimeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter
public class Category extends BaseTimeEntity {

	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
	private List<Post> posts;

}
