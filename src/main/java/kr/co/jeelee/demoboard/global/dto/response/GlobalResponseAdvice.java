package kr.co.jeelee.demoboard.global.dto.response;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "kr.co.jeelee.demoboard")
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(
		MethodParameter returnType,
		Class<? extends HttpMessageConverter<?>> converterType
	) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(
		Object body,
		MethodParameter returnType,
		MediaType selectedContentType,
		Class<? extends HttpMessageConverter<?>> selectedConverterType,
		ServerHttpRequest request,
		ServerHttpResponse response
	) {
		if (body instanceof GlobalResponse) {
			return body;
		}

		HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();

		int status = servletResponse.getStatus();
		HttpStatus httpStatus = HttpStatus.valueOf(status);

		if (httpStatus.is2xxSuccessful()) {
			return GlobalResponse.ok("Success", body);
		}

		return GlobalResponse.error("Error", body);
	}
}
