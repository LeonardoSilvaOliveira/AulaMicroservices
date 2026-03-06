package br.com.fiap.ms_produto.exception.handler;

import br.com.fiap.ms_produto.exception.DatabaseException;
import br.com.fiap.ms_produto.exception.ResourceNotFoundException;
import br.com.fiap.ms_produto.exception.dto.CustomErrorDto;
import br.com.fiap.ms_produto.exception.dto.ValidationErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDto> handlerResourceNotFound(ResourceNotFoundException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND; //404
        CustomErrorDto err = new CustomErrorDto(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDto> methodArgumentNotValid (MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDto err = new ValidationErrorDto(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());

        for(FieldError fieldError : e.getBindingResult().getFieldErrors()){
            err.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomErrorDto>handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorDto err = new CustomErrorDto(Instant.now(), status.value(), "Requisição inválida (JSON mau formado ou corpo não interpretavel).", request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CustomErrorDto>handleTypeMismatch(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDto err = new CustomErrorDto(Instant.now(), status.value(), "Requisito inválido(Parametro com tipo/ formato incorreto).", request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    //@ExceptionHandler(Exception.class)
    //public ResponseEntity<CustomErrorDto> handleGenericException(Exception e, HttpServletRequest request){
    //    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    //    CustomErrorDto err = new CustomErrorDto(Instant.now(), status.value(), "Erro interno INESPERADO.", request.getRequestURI());
    //    return ResponseEntity.status(status).body(err);
    //}

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomErrorDto> handleDatabase(DatabaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        CustomErrorDto err = new CustomErrorDto(Instant.now(), status.value(),
                e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
