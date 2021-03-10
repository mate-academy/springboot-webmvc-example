package mate.academy.resttesting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import mate.academy.resttesting.model.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
class UserControllerTest {
    public static final String USER_ENDPOINT = "/users";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void saveUser_ReturnsHttpStatusCreated() throws Exception {
        UserDto bobDto = new UserDto();
        bobDto.setName("Bob");
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(bobDto);
        mockMvc.perform(
                post(USER_ENDPOINT)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void getAll_ReturnsEmptyListOk() throws Exception {
        String content = "[]";
        mockMvc.perform(
                get(USER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(content));;
    }

    @Test
    void getAll_ReturnsListOk() throws Exception {
        UserDto bobDto = new UserDto();
        bobDto.setName("Bob");

        // Save user to DB
        ObjectMapper mapper = new ObjectMapper();
        String requestContent = mapper.writeValueAsString(bobDto);
        mockMvc.perform(
                post(USER_ENDPOINT)
                        .content(requestContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        // Verify user exists in the DB and method getAll works well
        bobDto.setId(1L);
        List<UserDto> dtos = List.of(bobDto);
        String responseContent = mapper.writeValueAsString(dtos);
        mockMvc.perform(
                get(USER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(responseContent));;
    }
}
