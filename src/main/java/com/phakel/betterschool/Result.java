package com.phakel.betterschool;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @author EvanLuo42
 * @date 7/13/22 10:26 AM
 */
@Data
public class Result {
    private Status status;
    private String message;
    private Object data;

    public ResponseEntity<Result> success(String message, Object data) {
        this.setStatus(Status.SUCCESS);
        this.setMessage(message);
        this.setData(data);
        return ResponseEntity.ok(this);
    }

    public ResponseEntity<Result> formError(String message, List<FieldError> error) {
        this.setStatus(Status.FORM_ERROR);
        this.setMessage(message);
        this.setData(error);
        return ResponseEntity.badRequest().body(this);
    }

    public ResponseEntity<Result> notFound(String message) {
        this.setStatus(Status.NOT_FOUND);
        this.setMessage(message);
        this.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this);
    }

    public ResponseEntity<Result> conflict(String message) {
        this.setStatus(Status.CONFLICT);
        this.setMessage(message);
        this.setData(null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(this);
    }

    public ResponseEntity<Result> unAuthorized(String message) {
        this.setStatus(Status.UNAUTHORIZED);
        this.setMessage(message);
        this.setData(null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(this);
    }

    public enum Status {
        SUCCESS, NOT_FOUND, FORM_ERROR, CONFLICT, UNAUTHORIZED
    }
}
