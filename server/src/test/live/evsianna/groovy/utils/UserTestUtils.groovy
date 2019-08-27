package live.evsianna.stylist.utils

import live.evsianna.stylist.model.dto.UserDTO
import live.evsianna.stylist.model.dto.UserFavorDTO

import live.evsianna.stylist.model.Favor
import live.evsianna.stylist.model.User

/**
 * @author Rustam Mamedov
 */

class UserTestUtils {

    static UserDTO getUserDTO() {
        UserDTO.builder()
                .firstName("User-111_first_name")
                .lastName("User-111_last_name")
                .email("user111@gmail.com")
                .phone("+17(800)100-10-10")
                .age(130)
                .build()
    }

    static UserFavorDTO getUserFavorDTO() {
        def user = User.builder()
                .firstName("User-111_first_name")
                .lastName("User-111_last_name")
                .email("user111@gmail.com")
                .phone("+17(800)100-10-10")
                .age(130)
                .build()

        def favor = Favor.builder()
                .title("bay course")
                .description("Hello! I wonna by.")
                .build()

        new UserFavorDTO(user, favor)
    }

    static UserFavorDTO getUserFavorDTOInvalid() {
        def user = User.builder()
                .firstName("U")
                .lastName("U")
                .email("@gmail.com")
                .phone("+7(800)")
                .age(-1)
                .build()

        def favor = Favor.builder()
                .title("ba")
                .description("He")
                .build()
        new UserFavorDTO(user, favor)
    }
}
