package live.evsianna.stylist.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Rustam Mamedov
 */

@Data
public class UsersRequestDTO {

    private final int page;

    private final int size;

    @JsonCreator
    public UsersRequestDTO(
            @JsonProperty("page") int page,
            @JsonProperty("size") int size) {
        this.page = page;
        this.size = size;
    }
}
