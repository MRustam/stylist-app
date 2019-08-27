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
import live.evsianna.stylist.model.dto.UserFavorDTO

import live.evsianna.stylist.service.interfaces.IUserService

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.verifyNoMoreInteractions
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static live.evsianna.stylist.utils.UserTestUtils.getUserFavorDTO

/**
 * @author Rustam Mamedov
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserServiceMockTest {

    private final static String FIND_ALL_USERS = "/api/user/all"

    private final static String REGISTER_USER_SAVE_FAVOR = "/api/user/save"

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private ObjectMapper mapper

    @MockBean
    private IUserService userService

    @Test
    @WithMockUser
    void RegisterUserAndSaveFavorTest() throws Exception {
        given:
        def dto = getUserFavorDTO()

        when:
        mockMvc.perform(post(REGISTER_USER_SAVE_FAVOR)
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        then:
        verify(userService, times(1)).saveNewUserWithFavor(any(UserFavorDTO.class))
        verifyNoMoreInteractions(userService)
    }

    @Test
    @WithMockUser
    void findAllUsersTest() throws Exception {
        given:
        def request = getUserRequestDTO(0, 20)

        when:
        mockMvc.perform(get(FIND_ALL_USERS)
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        then:
        verify(userService, times(1)).findAll(any(PageRequestDTO.class))
        verifyNoMoreInteractions(userService)
    }
}
