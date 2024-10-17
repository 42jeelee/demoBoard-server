package kr.co.jeelee.demoboard.domain.post.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import kr.co.jeelee.demoboard.domain.category.entity.CategoryEntity;
import kr.co.jeelee.demoboard.domain.comment.entity.CommentEntity;
import kr.co.jeelee.demoboard.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "POST")
@NoArgsConstructor
@Getter
public class PostEntity extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long postId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String author;

	private String password;

	@ManyToOne
	@JoinColumn(nullable = false)
	private CategoryEntity category;

	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<CommentEntity> comments;

	@Column
	private String content;

	@Column(nullable = false)
	private Long views;

	private PostEntity(String title, String author, String password, CategoryEntity category, String content) {
		this.title = title;
		this.author = author;
		this.password = password;
		this.category = category;
		this.content = content;
		this.views = 0L;
	}

	public static PostEntity of(String title, String author, String password, CategoryEntity category, String content) {
		return new PostEntity(title, author, password, category, content);
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
