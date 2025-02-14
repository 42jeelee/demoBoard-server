package kr.co.jeelee.demoboard.global.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.jeelee.demoboard.global.dto.response.ErrorResponse;
import kr.co.jeelee.demoboard.global.dto.response.GlobalResponse;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import org.springframework.http.MediaType;

import java.io.IOException;

public class FilterResponseHandler {

    public static void sendErrorResponse(
            final HttpServletResponse response,
            final CustomException ex
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        ErrorCode errorCode = ex.getErrorCode();
        response.setStatus(errorCode.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getCode(), null);
        GlobalResponse<ErrorResponse> globalResponse = GlobalResponse.error(errorCode.getMessage(), errorResponse);

        response.getWriter().write(objectMapper.writeValueAsString(globalResponse));
    }

}
