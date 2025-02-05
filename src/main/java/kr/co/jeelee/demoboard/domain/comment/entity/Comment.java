package kr.co.jeelee.demoboard.domain.comment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.co.jeelee.demoboard.domain.post.entity.Post;
import kr.co.jeelee.demoboard.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter
public class Comment extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String author;

	@Column(nullable = false)
	private String content;

	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	private Comment(String author, String content, Post post) {
		this.author = author;
		this.content = content;
		this.post = post;
	}

	public static Comment of(String author, String content, Post post) {
		return new Comment(author, content, post);
	}

}
