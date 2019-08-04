package live.evsianna.stylist.integration

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import live.evsianna.stylist.service.AppMailService

import static org.hamcrest.CoreMatchers.containsString
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static live.evsianna.stylist.utils.UserTestUtils.getUserOrderDTO
import static live.evsianna.stylist.utils.UserTestUtils.getUserOrderDTOInvalid
import static live.evsianna.stylist.utils.UserTestUtils.getUserRequestDTO

/**
 * @author Rustam Mamedov
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private final static String FIND_ALL_USERS = "/api/user/all"

    private final static String REGISTER_USER_SAVE_ORDER = "/api/user/save"

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private ObjectMapper mapper

    @MockBean
    private AppMailService mailService

    @Test
    @WithMockUser
    void "Register user and save order valid DTO"() throws Exception {
        given:
        def dto = getUserOrderDTO()

        when:
        def result = mockMvc.perform(post(REGISTER_USER_SAVE_ORDER)
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        then:
        result.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath('$.user.id').isNotEmpty())
                .andExpect(jsonPath('$.user.firstName').value("User-1_first_name"))
                .andExpect(jsonPath('$.user.lastName').value("User-1_last_name"))
                .andExpect(jsonPath('$.user.age').value(30))
                .andExpect(jsonPath('$.user.phone').value("+7(800)100-10-10"))
                .andExpect(jsonPath('$.user.email').value("user1@gmail.com"))
                .andExpect(jsonPath('$.user.password').isNotEmpty())
                .andExpect(jsonPath('$.user.created').isNotEmpty())
                .andExpect(jsonPath('$.user.updated').isNotEmpty())
                .andExpect(jsonPath('$.order.id').isNotEmpty())
                .andExpect(jsonPath('$.order.title').value("bay course"))
                .andExpect(jsonPath('$.order.message').value("Hello Anna! I wonna by your course."))
                .andExpect(jsonPath('$.order.created').isNotEmpty())
                .andExpect(jsonPath('$.order.updated').isNotEmpty())
                .andExpect(jsonPath('$.order.user').isNotEmpty())
    }

    @Test
    @WithMockUser
    void "Register user and save order invalid DTO"() throws Exception {
        given:
        def dto = getUserOrderDTOInvalid()

        when:
        def result = mockMvc.perform(post(REGISTER_USER_SAVE_ORDER)
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        then:
        result.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString("\"user.firstName\":\"Имя должно быть не меньше 2 и не больше 50 символов.\"")))
                .andExpect(content().string(containsString("\"user.lastName\":\"Фамилия должна быть не меньше 2 и не больше 50 символов.\"")))
                .andExpect(content().string(containsString("\"user.email\":\"Поле электронной почты должно быть корректным.\"")))
                .andExpect(content().string(containsString("\"user.age\":\"Возраст должен быть больше нуля.\"")))
                .andExpect(content().string(containsString("\"order.title\":\"Название услуги должно быть не меньше 5 и не больше 100 символов.")))
                .andExpect(content().string(containsString("\"order.message\":\"Сообщение должно быть не меньше 5 и не больше 2000 символов.\"")))
    }

    @Sql(value = "/create-user-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    @WithMockUser
    void "Find all users"() throws Exception {
        given:
        def dto = getUserRequestDTO(0, 20)

        when:
        def result = mockMvc.perform(get(FIND_ALL_USERS)
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        then:
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString("\"first\":true")))
                .andExpect(content().string(containsString("\"last\":true")))
                .andExpect(content().string(containsString("\"numberOfElements\":3")))
                .andExpect(content().string(containsString("\"totalPages\":1")))
                .andExpect(content().string(containsString("\"pageNumber\":0")))
                .andExpect(content().string(containsString("\"pageSize\":20")))
                .andExpect(content().string(containsString("User-1_first_name")))
                .andExpect(content().string(containsString("User-2_first_name")))
                .andExpect(content().string(containsString("User-3_first_name")))
    }
}
