package ts.andrey.tm.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ts.andrey.tm.AbstractIT;
import ts.andrey.tm.dto.TmPostDto;
import ts.andrey.tm.tmconst.ApiConst;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TmControllerIT extends AbstractIT {

    public static final TmPostDto TM_POST_DTO_GIVEN = TmPostDto.builder()
            .title("test head title")
            .message("test head message")
            .childPosts(List.of(TmPostDto.builder()
                    .title("test child title")
                    .message("test child message")
                    .build()))
            .build();

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Test
    void CreateTmPostMustReturn201AndSameDtoWithId() {
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post(ApiConst.ALL_TM_POSTS_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TM_POST_DTO_GIVEN))
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("Andrey", "test")))
                .andExpect(status().isCreated());
    }

    @SneakyThrows
    @Test
    void ReadTmPostMustReturn200AndAllDtoWithId() {
        //WHEN
        final var response = mockMvc.perform(MockMvcRequestBuilders.get(ApiConst.ALL_TM_POSTS_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("Andrey", "test")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //THEN
        List<TmPostDto> dtoList = objectMapper.readValue(response,
                objectMapper.getTypeFactory().constructCollectionType(List.class, TmPostDto.class));

        assertFalse(dtoList.isEmpty());

    }

}
