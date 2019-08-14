package live.evsianna.stylist.utils

import live.evsianna.stylist.model.dto.UserDTO
import live.evsianna.stylist.model.dto.UserOrderDTO

import live.evsianna.stylist.model.Order
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

    static UserOrderDTO getUserOrderDTO() {
        def user = User.builder()
                .firstName("User-111_first_name")
                .lastName("User-111_last_name")
                .email("user111@gmail.com")
                .phone("+17(800)100-10-10")
                .age(130)
                .build()

        def order = Order.builder()
                .title("bay course")
                .message("Hello! I wonna by.")
                .user(user)
                .build()

        new UserOrderDTO(user, order)
    }

    static UserOrderDTO getUserOrderDTOInvalid() {
        def user = User.builder()
                .firstName("U")
                .lastName("U")
                .email("@gmail.com")
                .phone("+7(800)")
                .age(-1)
                .build()

        def order = Order.builder()
                .title("ba")
                .message("He")
                .user(user)
                .build()
        new UserOrderDTO(user, order)
    }
}
