package com.bank.app.bankNTI.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(BranchNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse>handleBranchNotFound(BranchNotFound e){
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse>handleUserNotFound(UserNotFound e){
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(insufficientBalance.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse>handleinsufficientBalance(insufficientBalance e){
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse>handleAccountNotFound(AccountNotFound e){
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleGeneralException() {
        ErrorResponse error = new ErrorResponse();
        error.setMessage("Something went wrong");
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
