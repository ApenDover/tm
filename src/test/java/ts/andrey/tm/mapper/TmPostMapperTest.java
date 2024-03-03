package ts.andrey.tm.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ts.andrey.tm.dto.TmPostDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TmPostMapperTest {

    private final TmPostMapper mapper =
            Mappers.getMapper(TmPostMapper.class);

    @Test
    void mapperTest() {

        // GIVEN
        final var tmPostDtoGiven = TmPostDto.builder()
                .title("test head title")
                .message("test head message")
                .childPosts(List.of(TmPostDto.builder()
                        .title("test child title")
                        .message("test child message")
                        .build()))
                .build();

        //WHEN
        final var tmPostActual = mapper.toEntity(tmPostDtoGiven);
        final var tmPostDtoActual = mapper.toDto(tmPostActual);

        //THEN
        assertEquals(tmPostDtoGiven.getTitle(), tmPostActual.getTitle());
        assertEquals(tmPostDtoGiven.getMessage(), tmPostActual.getMessage());
        assertEquals(tmPostDtoGiven.getChildPosts().size(), tmPostActual.getChildPosts().size());
        assertEquals(tmPostDtoGiven.getChildPosts().get(0).getTitle(), tmPostActual.getChildPosts().get(0).getTitle());
        assertEquals(tmPostDtoGiven.getChildPosts().get(0).getMessage(), tmPostActual.getChildPosts().get(0).getMessage());
        assertEquals(tmPostDtoGiven, tmPostDtoActual);

    }

}