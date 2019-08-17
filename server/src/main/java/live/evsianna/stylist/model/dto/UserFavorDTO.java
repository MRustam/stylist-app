package live.evsianna.stylist.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import live.evsianna.stylist.model.Favor;
import live.evsianna.stylist.model.User;
import lombok.Data;

import javax.validation.Valid;

/**
 * @author Rustam Mamedov
 */

@Data
public class UserFavorDTO {

    @Valid
    private final User user;

    @Valid
    private final Favor favor;

    @JsonCreator
    public UserFavorDTO(@Valid @JsonProperty("user") User user,
                        @Valid @JsonProperty("favor") Favor favor) {
        this.user = user;
        this.favor = favor;
    }
}
