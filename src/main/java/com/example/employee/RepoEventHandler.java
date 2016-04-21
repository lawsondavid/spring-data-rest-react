package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by dave on 21/04/16.
 */

@RepositoryEventHandler
@Component
public class RepoEventHandler {

    private final Validator validator;

    @Autowired
    public RepoEventHandler(Validator validator){
        this.validator = validator;
    }

    @HandleBeforeCreate
    public void handleCreation(Employee employee) {
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(employee);
        if( !constraintViolationSet.isEmpty() ){
            throw new ConstraintViolationException(constraintViolationSet);
        }
    }
}