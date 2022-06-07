package com.lewisCode.hostelbookingsystem.exeptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {
    private final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    private final HttpStatus notFound = HttpStatus.NOT_FOUND;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setTimestamp(LocalDateTime.now());
        exceptionMessage.setError(badRequest);
        exceptionMessage.setStatus(badRequest.value());
        exceptionMessage.setMessage(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
        exceptionMessage.setPath(((ServletWebRequest) request).getRequest().getServletPath());

        return new ResponseEntity<>(exceptionMessage,badRequest);
    }
    @ExceptionHandler(value = UserExistException .class)
    public ResponseEntity<Object>userExistException(HttpServletRequest request,
                                                    UserExistException  e) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(
                LocalDateTime.now(),
                badRequest.value(),
                badRequest,
                e.getMessage(),
                request.getServletPath());
        return new ResponseEntity<>(exceptionMessage, badRequest);
    }
    @ExceptionHandler(value = UserNotFound .class)
    public ResponseEntity<Object>userNotFound(HttpServletRequest request,
                                                    UserNotFound  e) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(
                LocalDateTime.now(),
                notFound.value(),
                notFound,
                e.getMessage(),
                request.getServletPath());
        return new ResponseEntity<>(exceptionMessage, notFound);
    }
}
