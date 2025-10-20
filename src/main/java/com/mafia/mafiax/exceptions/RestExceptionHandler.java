package com.mafia.mafiax.config;

import com.mafia.mafiax.exceptions.RoomIsFullException;
import com.mafia.mafiax.exceptions.RoomNotFoundException;
import com.mafia.mafiax.exceptions.UserNotFoundException;
import com.mafia.mafiax.exceptions.PlayerNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    // Обработка исключений, связанных с отсутствием ресурса (404)
    @ExceptionHandler({
            UserNotFoundException.class,
            RoomNotFoundException.class,
            PlayerNotFoundException.class
    })
    public ResponseEntity<Object> handleNotFoundException(RuntimeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // (400 Bad Request)
    @ExceptionHandler({
            RoomIsFullException.class,
            IllegalStateException.class,
            DataIntegrityViolationException.class
    })
    public ResponseEntity<Object> handleBadRequestException(RuntimeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}