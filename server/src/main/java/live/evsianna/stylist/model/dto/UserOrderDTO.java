package live.evsianna.stylist.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import live.evsianna.stylist.model.Order;
import live.evsianna.stylist.model.User;
import lombok.Data;

import javax.validation.Valid;

/**
 * @author Rustam Mamedov
 */

@Data
public class UserOrderDTO {

    @Valid
    private final User user;

    @Valid
    private final Order order;

    @JsonCreator
    public UserOrderDTO(@Valid @JsonProperty("user") User user,
                        @Valid @JsonProperty("order") Order order) {
        this.user = user;
        this.order = order;
    }
}
