package live.evsianna.stylist.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
class EntityErrorResponse<T> {

    private int status;

    private String message;

    private LocalDateTime created;

}
