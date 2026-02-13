package com.EmployeeManagement.Exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException e,
			WebRequest wb) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	// @ExceptionHandler(MethodArgumentNotValidException.class)
	// public ResponseEntity<ErrorDetails> handleValidationExceptions(MethodArgumentNotValidException ex) {
	//     Map<String, String> errors = new LinkedHashMap<>();
	//     ex.getBindingResult().getAllErrors().forEach((error) -> {
	//         String fieldName = ((FieldError) error).getField();
	//         String message = error.getDefaultMessage(); // Annotation message
	//         errors.put(fieldName, message);
	//     });

	//     ErrorDetails errorDetails = new ErrorDetails(
	//         LocalDateTime.now(),
	//         "Validation failed for one or more fields",
	//         errors
	//     );

	//     return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	// }

	// @ExceptionHandler(DataIntegrityViolationException.class)
    // public ResponseEntity<String> handleDataIntegrity(DataIntegrityViolationException ex) {
    //     return ResponseEntity.badRequest().body(ex.getRootCause().getMessage());
    // }

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    
}
