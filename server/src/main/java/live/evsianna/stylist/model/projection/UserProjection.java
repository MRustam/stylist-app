package live.evsianna.stylist.model.projection;

import java.time.LocalDateTime;

/**
 * @author Rustam Mamedov
 */

public interface UserProjection {

    String getFirstName();

    String getLastName();

    String getEmail();

    String getPassword();

    String getPhone();

    int getAge();

    LocalDateTime getCreated();

    LocalDateTime getUpdated();

}
