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
import live.evsianna.stylist.model.Favor
import live.evsianna.stylist.repository.UserRepository
import live.evsianna.stylist.service.interfaces.IFavorService
import live.evsianna.stylist.service.AppMailService

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.verifyNoMoreInteractions
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static live.evsianna.stylist.utils.UserTestUtils.getUserFavorDTO

/**
 * @author Rustam Mamedov
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FavorServiceMockTest {

    private final static String REGISTER_USER_SAVE_FAVOR = "/api/user/save"

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private ObjectMapper mapper

    @MockBean
    private UserRepository userRepository

    @MockBean
    private IFavorService favorService

    @MockBean
    private AppMailService mailService

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
        verify(favorService, times(1)).save(any(Favor.class))
        verifyNoMoreInteractions(favorService)
    }
}
