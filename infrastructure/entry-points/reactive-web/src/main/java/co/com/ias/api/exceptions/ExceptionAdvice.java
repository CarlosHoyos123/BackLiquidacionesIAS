package co.com.ias.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDTO> IllegalArgument(IllegalArgumentException exception) {
        ExceptionDTO response = new ExceptionDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }/* return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);*/
}
