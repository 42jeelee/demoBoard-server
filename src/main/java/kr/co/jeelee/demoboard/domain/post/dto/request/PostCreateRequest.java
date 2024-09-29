package kr.co.jeelee.demoboard.domain.post.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostCreateRequest {

	@NotBlank(message = "Name cannot be blank.")
	private final String title;

	@NotNull(message = "Author cannot be NULL.")
	private final String author;

	@NotBlank(message = "Password cannot be blank.")
	@Size(min = 8, max = 20, message = "Password must be 8 - 20 characters.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).+$", message = "Password must contain at least one number/letter/special character.")
	private final String password;

	private final String content;
}
