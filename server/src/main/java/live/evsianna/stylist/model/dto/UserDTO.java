package live.evsianna.stylist.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Rustam Mamedov
 */

@Data
@NoArgsConstructor
public class UserDTO {

    private String id;

    private String firstName;

    private String lastName;

    private int age;

    private String phone;

    private String email;

    private LocalDateTime created;

    private LocalDateTime updated;

    @Builder
    public UserDTO(String firstName, String lastName,
                   int age, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }
}
