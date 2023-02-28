package com.example.demo.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR(2000, "Internal Server Error", INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(2001, "Invalid Parameter Error", BAD_REQUEST),
    ENTITY_NOT_SAVED(2002, "Invalid Parameter Error", BAD_REQUEST),

    BOOK_NOT_FOUND(3001, "Book is not found", BAD_REQUEST),
    BOOK_IS_EXIST(3002, "Book is already exist", BAD_REQUEST),
    BORROWER_NOT_FOUND(3003, "Borrower is not found", BAD_REQUEST),
    BORROWER_IS_EXIST(3004, "Borrower is already exist", BAD_REQUEST),
    BOOK_NOT_AVAILABLE(3005, "Book is not available", BAD_REQUEST),
    NOT_BORROWED_BY(3006, "This book is not borrowed by this person", BAD_REQUEST);

    private int code;
    private String message;
    HttpStatus httpStatus;

}