package nl.gerimedica.handler;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import nl.gerimedica.exception.NotFoundException;
import nl.gerimedica.exception.UnsupportedFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        log.error("Unexpected exception ", ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionResponse responseBody = ExceptionResponse.builder()
                .message("Internal system error").build();
        return ResponseEntity.status(status).body(responseBody);
    }

    @ExceptionHandler(UnsupportedFormatException.class)
    public ResponseEntity<ExceptionResponse> handleUnsupportedFormatException(UnsupportedFormatException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse responseBody = ExceptionResponse.builder()
                .message(ex.getMessage()).build();
        return ResponseEntity.status(status).body(responseBody);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse responseBody = ExceptionResponse.builder()
                .message(ex.getMessage()).build();
        return ResponseEntity.status(status).body(responseBody);
    }

}
