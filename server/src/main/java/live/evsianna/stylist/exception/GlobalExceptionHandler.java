package live.evsianna.stylist.exception;

import live.evsianna.stylist.model.User;
import live.evsianna.stylist.model.projection.FavorProjection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<EntityErrorResponse<User>> handleUserNotFound(final UserNotFoundException e) {
        final EntityErrorResponse<User> entityErrorResponse = new EntityErrorResponse<>();
        entityErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        entityErrorResponse.setMessage(e.getMessage());
        entityErrorResponse.setCreated(LocalDateTime.now());
        return new ResponseEntity<>(entityErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<EntityErrorResponse> handleOtherExceptions(final Exception e) {
        final EntityErrorResponse entityErrorResponse = new EntityErrorResponse<>();
        entityErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        entityErrorResponse.setMessage(e.getMessage());
        entityErrorResponse.setCreated(LocalDateTime.now());
        return new ResponseEntity<>(entityErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotCreatedException.class)
    public ResponseEntity<EntityErrorResponse<User>> handleUserNotCreated(final UserNotCreatedException e) {
        final EntityErrorResponse<User> entityErrorResponse = new EntityErrorResponse<>();
        entityErrorResponse.setStatus(HttpStatus.CONFLICT.value());
        entityErrorResponse.setMessage(e.getMessage());
        entityErrorResponse.setCreated(LocalDateTime.now());
        return new ResponseEntity<>(entityErrorResponse, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserIsNotAuthorizedException.class)
    public ResponseEntity<EntityErrorResponse<User>> handleUserNotAuthorized(final UserIsNotAuthorizedException e) {
        final EntityErrorResponse<User> entityErrorResponse = new EntityErrorResponse<>();
        entityErrorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        entityErrorResponse.setMessage(e.getMessage());
        entityErrorResponse.setCreated(LocalDateTime.now());
        return new ResponseEntity<>(entityErrorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FavorNotFoundException.class)
    public ResponseEntity<EntityErrorResponse<FavorProjection>> handleOrderNotFoundExceptions(final FavorNotFoundException e) {
        final EntityErrorResponse<FavorProjection> entityErrorResponse = new EntityErrorResponse<>();
        entityErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        entityErrorResponse.setMessage(e.getMessage());
        entityErrorResponse.setCreated(LocalDateTime.now());
        return new ResponseEntity<>(entityErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidExceptions(final MethodArgumentNotValidException e) {
        final Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((err) -> {
            final String fieldName = ((FieldError) err).getField();
            final String errorMessage = err.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errors.put("time", LocalDateTime.now().toString());
        return errors;
    }

}
