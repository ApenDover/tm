package ts.andrey.tm.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.CollectionUtils;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ts.andrey.tm.dto.TmPostDto;
import ts.andrey.tm.tmconst.ApiConst;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class TmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSendAndReadDto() throws Exception {

        //GIVEN
        final var tmPostDtoGiven = TmPostDto.builder()
                .title("test head title")
                .message("test head message")
                .childPosts(List.of(TmPostDto.builder()
                        .title("test child title")
                        .message("test child message")
                        .build()))
                .build();

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post(ApiConst.ALL_CREATE_POST_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tmPostDtoGiven))
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("Andrey", "test")))
                .andExpect(status().isOk());

        String response = mockMvc.perform(MockMvcRequestBuilders.get(ApiConst.ALL_GET_POST_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("Andrey", "test")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //THEN
        List<TmPostDto> dtoList = objectMapper.readValue(response,
                objectMapper.getTypeFactory().constructCollectionType(List.class, TmPostDto.class));

        final var result = CollectionUtils.lastElement(dtoList);

        assertEquals(tmPostDtoGiven.getMessage(), result.getMessage());
        assertEquals(tmPostDtoGiven.getTitle(), result.getTitle());
        assertEquals(tmPostDtoGiven.getChildPosts().get(0).getTitle(), result.getChildPosts().get(0).getTitle());
        assertEquals(tmPostDtoGiven.getChildPosts().get(0).getMessage(), result.getChildPosts().get(0).getMessage());

    }

}
