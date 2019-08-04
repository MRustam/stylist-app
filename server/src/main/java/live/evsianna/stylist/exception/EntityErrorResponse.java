package live.evsianna.stylist.exception;

import lombok.Data;

@Data
class EntityErrorResponse<T> {

    private int status;

    private String message;

    private Long timeStamp;

}
