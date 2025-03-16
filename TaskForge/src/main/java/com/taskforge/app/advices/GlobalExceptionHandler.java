package com.taskforge.app.advices;

import com.taskforge.app.exceptions.ResourceNotFound;
import com.taskforge.app.utils.LoggerUtil;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends LoggerUtil {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGlobalException(Exception exception) {
        ApiError apiError = ApiError.builder()
                .errors(List.of(exception.getMessage()))
                .message("ERROR")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        logger.error("Global Exception occurred. Check the error response for more information.");
        return getResponseEntityObject(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ApiError apiError = ApiError.builder()
                .errors(errors)
                .message("INPUT_VALIDATION_FAILED")
                .status(HttpStatus.BAD_REQUEST)
                .build();

        logger.error("Input Validation Failed. Client has sent invalid data to the Controller.");
        return getResponseEntityObject(apiError);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNoResourceNotFoundException(NoResourceFoundException exception) {
        ApiError apiError = ApiError.builder()
                .message("INVALID_ENDPOINT")
                .errors(List.of(exception.getMessage()))
                .status(HttpStatus.NOT_FOUND)
                .build();

        logger.error("Client requested for invalid resource endpoint.");
        return getResponseEntityObject(apiError);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFound exception) {
        ApiError apiError = ApiError.builder()
                .message("RESOURCE_NOT_FOUND")
                .errors(List.of(exception.getMessage()))
                .status(HttpStatus.NOT_FOUND)
                .build();

        logger.error("Resource Not Found. Client is requesting data for id that does not exists.");
        return getResponseEntityObject(apiError);
    }

    private ResponseEntity<ApiResponse<?>> getResponseEntityObject(ApiError apiError) {
        ApiResponse<ApiError> response = new ApiResponse<>(apiError);
        return new ResponseEntity<>(response, apiError.getStatus());
    }
}
