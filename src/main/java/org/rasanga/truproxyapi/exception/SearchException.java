package org.rasanga.truproxyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SearchException extends RuntimeException{
    public SearchException(String message) {
        super(message);
    }
}
