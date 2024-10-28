package kr.muve.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 유효성 검사 실패 시 발생하는 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        // 유효성 검증에서 실패한 필드와 메시지를 모아 응답
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        // 에러 메시지를 JSON으로 반환
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // HttpMessageNotReadableException 처리
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex, WebRequest request) {

        Map<String, String> response = new HashMap<>();
        response.put("error", "Invalid request body");
        response.put("message", "Request body is missing or malformed. Please check your input.");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 다른 예외를 처리할 수 있는 핸들러 추가 가능
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
//        Map<String, String> response = new HashMap<>();
//        response.put("error", "Internal server error");
//        response.put("message", ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}