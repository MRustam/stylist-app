package live.evsianna.stylist.mock

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import live.evsianna.stylist.model.Order
import live.evsianna.stylist.repository.UserRepository
import live.evsianna.stylist.service.interfaces.IOrderService
import live.evsianna.stylist.service.AppMailService

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.verifyNoMoreInteractions
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static live.evsianna.stylist.utils.UserTestUtils.getUserOrderDTO

/**
 * @author Rustam Mamedov
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderServiceMockTest {

    private final static String REGISTER_USER_SAVE_ORDER = "/api/user/save"

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private ObjectMapper mapper

    @MockBean
    private UserRepository userRepository

    @MockBean
    private IOrderService orderService

    @MockBean
    private AppMailService mailService

    @Test
    @WithMockUser
    void RegisterUserAndSaveOrderTest() throws Exception {
        given:
        def dto = getUserOrderDTO()

        when:
        mockMvc.perform(post(REGISTER_USER_SAVE_ORDER)
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        then:
        verify(orderService, times(1)).save(any(Order.class))
        verifyNoMoreInteractions(orderService)
    }
}
