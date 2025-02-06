package kr.co.jeelee.demoboard.domain.post.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import kr.co.jeelee.demoboard.domain.category.entity.Category;
import kr.co.jeelee.demoboard.domain.comment.entity.Comment;
import kr.co.jeelee.demoboard.domain.member.entity.Member;
import kr.co.jeelee.demoboard.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
public class Post extends BaseTimeEntity {

	@Column(nullable = false)
	private String title;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Member author;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<Comment> comments;

	@Column
	private String content;

	@Column(nullable = false)
	private Long views;

	private Post(String title, Member author, Category category, String content) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.content = content;
		this.views = 0L;
	}

	public static Post of(String title, Member author, Category category, String content) {
		return new Post(title, author, category, content);
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
