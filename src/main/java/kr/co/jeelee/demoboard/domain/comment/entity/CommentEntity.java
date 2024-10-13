package kr.co.jeelee.demoboard.domain.comment.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.co.jeelee.demoboard.domain.post.entity.PostEntity;
import kr.co.jeelee.demoboard.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Comment")
@NoArgsConstructor
@Getter
public class CommentEntity extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;

	@Column(nullable = false)
	private String author;

	@Column(nullable = false)
	private String content;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(nullable = false)
	private PostEntity post;

	private CommentEntity(String author, String content, PostEntity post) {
		this.author = author;
		this.content = content;
		this.post = post;
	}

	public static CommentEntity of(String author, String content, PostEntity post) {
		return new CommentEntity(author, content, post);
	}

}
