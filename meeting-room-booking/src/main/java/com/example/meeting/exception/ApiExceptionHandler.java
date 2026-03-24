package com.example.meeting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {
        return ResponseEntity.badRequest().body(
            Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "error", ex.getMessage()
            )
        );
    }

    @ExceptionHandler(AlreadyBookedException.class)
    public ResponseEntity<?> handleAlreadyBookedException(AlreadyBookedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "error", ex.getMessage()
            )
        );
    }

    @ExceptionHandler(UnbookException.class)
    public ResponseEntity<?> handleUnbookException(UnbookException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
            Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "error", ex.getMessage()
            )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> 
            errors.put(err.getField(), err.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(
            Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "validationErrors", errors
            )
        );
    }
}