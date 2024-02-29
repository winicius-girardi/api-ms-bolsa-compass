package com.compassuol.sp.challenge.msuser.exception;


import com.compassuol.sp.challenge.msuser.exception.customexceptions.ConstraintViolationException;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.EntityNotFoundException;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.PublisherRequestException;
import com.compassuol.sp.challenge.msuser.exception.customexceptions.UserValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({UserValidationException.class})
    public ResponseEntity<ErrorMessage> userValidationException(UserValidationException ex,
                                                                        HttpServletRequest request,
                                                                        BindingResult result) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), result));
    }

    @ExceptionHandler({PublisherRequestException.class})
    public ResponseEntity<ErrorMessage> publisherException(RuntimeException ex,
                                                                         HttpServletRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Ocorreu algum erro com o publisher\n"+ex.getMessage()));

    }
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorMessage> constraintViolationException(ConstraintViolationException ex,
                                                                     HttpServletRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));

    }

    @ExceptionHandler({EntityNotFoundException.class, org.springframework.web.servlet.resource.NoResourceFoundException.class})
    public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException ex,
                                                                HttpServletRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));

    }


    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorMessage> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.METHOD_NOT_ALLOWED, "Método não permitido para esta rota"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> internalServerErrorException(Exception ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor"));
    }
}
