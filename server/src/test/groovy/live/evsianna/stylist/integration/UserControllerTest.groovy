package live.evsianna.stylist.integration

import com.fasterxml.jackson.databind.ObjectMapper
import live.evsianna.stylist.service.AppMailService
import live.evsianna.stylist.service.interfaces.IUserService
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

import static live.evsianna.stylist.utils.UserTestUtils.getUserDTO
import static live.evsianna.stylist.utils.UserTestUtils.getUserOrderDTO
import static live.evsianna.stylist.utils.UserTestUtils.getUserOrderDTOInvalid
import static org.hamcrest.CoreMatchers.containsString
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * @author Rustam Mamedov
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private final static String FIND_ALL_USERS = "/api/user/all"

    private final static String FIND_USERS_BY_ID = "/api/user/by-id/{id}"

    private final static String REGISTER_USER_SAVE_ORDER = "/api/user/save"

    private final static String REGISTER_USER = "/api/user/save/simple"

    private final static String SET_ENABLED_BY_ID = "/api/user/enabled/{id}/{enabled}"

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private ObjectMapper mapper

    @Autowired
    private IUserService userService

    @MockBean
    private AppMailService mailService

    @Sql(value = "/before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @WithMockUser
    void "Find all users"() throws Exception {

        when:
        def result = mockMvc.perform(get(FIND_ALL_USERS)
                .param("page", "0")
                .param("size", "20"))
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

    @Sql(value = "/before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @WithMockUser
    void "Find by id"() throws Exception {

        when:
        def result = mockMvc.perform(get(FIND_USERS_BY_ID, "uId-1"))

        then:
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath('$.id').value("uId-1"))
                .andExpect(jsonPath('$.firstName').value("User-1_first_name"))
                .andExpect(jsonPath('$.lastName').value("User-1_last_name"))
    }

    @Sql(value = "/before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @WithMockUser
    void "Set Enabled by Id"() throws Exception {
        given:
        final String id = "uId-1"

        when:
        mockMvc.perform(patch(SET_ENABLED_BY_ID, id, true))
        def user = userService.findById(id)

        then:
        !user.enabled
    }

    @Sql(value = "/after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @WithMockUser
    void "Register New user"() throws Exception {
        given:
        def dto = getUserDTO()

        when:
        def result = mockMvc.perform(post(REGISTER_USER)
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        then:
        result.andDo(print())
                .andExpect(status().isCreated())
    }

    @Sql(value = "/after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
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
                .andExpect(jsonPath('$.user.firstName').value("User-111_first_name"))
                .andExpect(jsonPath('$.user.lastName').value("User-111_last_name"))
                .andExpect(jsonPath('$.user.age').value(130))
                .andExpect(jsonPath('$.user.phone').value("+17(800)100-10-10"))
                .andExpect(jsonPath('$.user.email').value("user111@gmail.com"))
                .andExpect(jsonPath('$.user.created').isNotEmpty())
                .andExpect(jsonPath('$.user.updated').isNotEmpty())
                .andExpect(jsonPath('$.order.title').value("bay course"))
                .andExpect(jsonPath('$.order.message').value("Hello! I wonna by."))
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
}
