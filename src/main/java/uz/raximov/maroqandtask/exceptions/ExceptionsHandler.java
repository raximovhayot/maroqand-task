package uz.raximov.maroqandtask.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.raximov.maroqandtask.payload.response.ApiResult;
import uz.raximov.maroqandtask.payload.response.ErrorData;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResult<List<ErrorData>>> handleException(MethodArgumentNotValidException ex) {
        List<ErrorData> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(new ErrorData(errorMessage, HttpStatus.BAD_REQUEST.value()));
        });
        return new ResponseEntity<>(new ApiResult<>(errors), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<ApiResult<List<ErrorData>>> handleException(RestException ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getUserMsg(),
                        ex.getStatus().value()),
                ex.getStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiResult<List<ErrorData>>> handleException(Exception ex) {
        return new ResponseEntity<>(
                new ApiResult<>(ex.getMessage(), HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ApiResult<List<ErrorData>>> handleException() {
        return new ResponseEntity<>(
                new ApiResult<>("Your request body is invalid!", HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }
}