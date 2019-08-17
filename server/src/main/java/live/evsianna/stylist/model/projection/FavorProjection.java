package live.evsianna.stylist.model.projection;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Rustam Mamedov
 */

public interface FavorProjection {

    String getId();

    String getTitle();

    String getDescription();

    BigDecimal getPrice();

    Integer getPopularity();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    LocalDateTime getCreated();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    LocalDateTime getUpdated();

}
