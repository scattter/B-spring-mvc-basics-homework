package com.thoughtworks.capacity.gtb.mvc.Exception;

import com.thoughtworks.capacity.gtb.mvc.Exception.ErrorResult;
import com.thoughtworks.capacity.gtb.mvc.Exception.UserNameAlreadyExistsException;
import com.thoughtworks.capacity.gtb.mvc.Exception.UserNameOrPasswordIsErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST.value(), message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler({UserNameAlreadyExistsException.class,
            UserNameOrPasswordIsErrorException.class})
    public ResponseEntity<ErrorResult> handle(RuntimeException ex) {
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResult> handle(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        String message = "";
        for (ConstraintViolation<?> constraint : ex.getConstraintViolations()) {
            message = constraint.getMessage();
            break;
        }
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST.value(), message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
