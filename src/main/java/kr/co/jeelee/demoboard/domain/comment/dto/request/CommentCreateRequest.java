package kr.co.jeelee.demoboard.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentCreateRequest {

    @NotBlank(message = "author can't be Blank.")
    private final String author;

    @NotNull(message = "content can't be Null.")
    private final String content;

}
