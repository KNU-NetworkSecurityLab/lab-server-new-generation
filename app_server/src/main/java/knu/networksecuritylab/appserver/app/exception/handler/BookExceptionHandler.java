package knu.networksecuritylab.appserver.app.exception.handler;

import knu.networksecuritylab.appserver.app.exception.handler.dto.ErrorResponseDto;
import knu.networksecuritylab.appserver.app.exception.book.BookException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler(BookException.class)
    protected ResponseEntity<ErrorResponseDto> bookExceptionHandler(
            BookException e, HttpServletRequest request
    ) {
        List<String> messages = new ArrayList<>();
        messages.add(e.getBookErrorCode().getMessage());
        log.error("Messages = {}", messages);

        ErrorResponseDto errorResponseDto = createErrorResponseDto(
                e.getBookErrorCode().getHttpStatus(), messages, request);
        return ResponseEntity.status(errorResponseDto.getStatusCode()).body(errorResponseDto);
    }

    private ErrorResponseDto createErrorResponseDto(
            HttpStatus status, List<String> messages, HttpServletRequest request
    ) {
        String requestURI = request.getRequestURI();

        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .statusCode(status.value())
                .errorType(status.name())
                .path(requestURI)
                .build();
        messages.forEach(errorResponseDto::addMessage);
        return errorResponseDto;
    }
}
