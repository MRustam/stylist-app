package live.evsianna.stylist.exception;

import live.evsianna.stylist.model.projection.OrderProjection;
import live.evsianna.stylist.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse<User>> handleUserNotFound(final UserNotFoundException e) {
        final EntityErrorResponse<User> entityErrorResponse = new EntityErrorResponse<>();
        entityErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        entityErrorResponse.setMessage(e.getMessage());
        entityErrorResponse.setCreated(LocalDateTime.now());
        return new ResponseEntity<>(entityErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse<User>> handleUserNotCreated(final UserNotCreatedException e) {
        final EntityErrorResponse<User> entityErrorResponse = new EntityErrorResponse<>();
        entityErrorResponse.setStatus(HttpStatus.CONFLICT.value());
        entityErrorResponse.setMessage(e.getMessage());
        entityErrorResponse.setCreated(LocalDateTime.now());
        return new ResponseEntity<>(entityErrorResponse, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse<OrderProjection>> handleOrderNotFoundExceptions(final OrderNotFoundException e) {
        final EntityErrorResponse<OrderProjection> entityErrorResponse = new EntityErrorResponse<>();
        entityErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        entityErrorResponse.setMessage(e.getMessage());
        entityErrorResponse.setCreated(LocalDateTime.now());
        return new ResponseEntity<>(entityErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Map<String, String> handleValidExceptions(final MethodArgumentNotValidException e) {
        final Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((err) -> {
            final String fieldName = ((FieldError) err).getField();
            final String errorMessage = err.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errors.put("time", dtf.format(LocalDateTime.now()));
        return errors;
    }

}
