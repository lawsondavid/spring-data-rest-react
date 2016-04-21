package com.example.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by dave on 21/04/16.
 */
@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Collection<String> validationError(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map( ex -> ex.getPropertyPath() + " " + ex.getMessage())
                .collect(Collectors.toList());
    }
}
