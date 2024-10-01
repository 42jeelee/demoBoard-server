package kr.co.jeelee.demoboard.global.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import kr.co.jeelee.demoboard.global.dto.response.ErrorResponse;
import kr.co.jeelee.demoboard.global.dto.response.GlobalResponse;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorCode errorCode = ErrorCode.POST_ARGUMENTS_INVALID;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getCode(), errors);
        GlobalResponse<ErrorResponse> globalResponse = GlobalResponse.error(errorCode.getMessage(), errorResponse);

        return ResponseEntity.status(errorCode.getStatus()).body(globalResponse);
    }

    @ExceptionHandler(value = { CustomException.class })
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getCode(), null);
        GlobalResponse<ErrorResponse> globalResponse = GlobalResponse.error(errorCode.getMessage(), errorResponse);

        return ResponseEntity.status(errorCode.getStatus()).body(globalResponse);
    }
}
