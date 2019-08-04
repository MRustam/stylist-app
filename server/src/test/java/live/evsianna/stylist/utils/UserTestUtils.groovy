package live.evsianna.stylist.utils

import live.evsianna.stylist.controller.model.UserOrderDTO
import live.evsianna.stylist.controller.model.UsersRequestDTO
import live.evsianna.stylist.model.Order
import live.evsianna.stylist.model.User

/**
 * @author Rustam Mamedov
 */

class UserTestUtils {

    static UsersRequestDTO getUserRequestDTO(def page, def size) {
        new UsersRequestDTO(page, size)
    }

    static UserOrderDTO getUserOrderDTO() {
        def user = User.builder()
                .firstName("User-1_first_name")
                .lastName("User-1_last_name")
                .email("user1@gmail.com")
                .phone("+7(800)100-10-10")
                .age(30)
                .build()

        def order = Order.builder()
                .title("bay course")
                .message("Hello Anna! I wonna by your course.")
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
