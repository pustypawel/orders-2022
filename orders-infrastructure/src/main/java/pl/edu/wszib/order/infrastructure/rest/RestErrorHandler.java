package pl.edu.wszib.order.infrastructure.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.edu.wszib.order.api.ErrorApi;

@RestControllerAdvice
@Slf4j
public class RestErrorHandler {
    //See: DefaultHandlerExceptionResolver

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        final BindingResult bindingResult = e.getBindingResult();
        return ResponseEntity.badRequest()
                .body(new ErrorApi("BAD_REQUEST", bindingResult.getAllErrors().toString()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> handleException(final Exception e) {
        log.error("Unexpected error", e);
        return ResponseEntity.internalServerError()
                .body(new ErrorApi("UNEXPECTED_ERROR", e.getMessage()));
    }
}
