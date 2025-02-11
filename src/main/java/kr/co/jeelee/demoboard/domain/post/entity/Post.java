package kr.co.jeelee.demoboard.domain.post.entity;

import jakarta.persistence.*;

import kr.co.jeelee.demoboard.domain.comment.entity.Comment;
import kr.co.jeelee.demoboard.domain.member.entity.Member;
import kr.co.jeelee.demoboard.domain.relationship.postCategory.entity.PostCategory;
import kr.co.jeelee.demoboard.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
public class Post extends BaseEntity {

	@Column(nullable = false)
	private String title;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Member author;

	@OneToMany(mappedBy = "a", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PostCategory> postCategories;

	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Comment> comments;

	@Lob @Column
	private String content;

	@Column(nullable = false)
	private Long views;

	private Post(String title, Member author, String content) {
		this.title = title;
		this.author = author;
		this.postCategories = new ArrayList<>();
		this.comments = new ArrayList<>();
		this.content = content;
		this.views = 0L;
	}

	public static Post of(String title, Member author, String content) {
		return new Post(title, author, content);
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
