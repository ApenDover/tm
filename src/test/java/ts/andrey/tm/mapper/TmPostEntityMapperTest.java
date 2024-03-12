package ts.andrey.tm.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ts.andrey.tm.dto.TmPostDto;
import ts.andrey.tm.entity.TmPostEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TmPostEntityMapperTest {

    private final TmPostMapper mapper = Mappers.getMapper(TmPostMapper.class);

    @Test
    void toEntity() {
        //GIVEN
        final TmPostDto tmPostDto = TmPostDto.builder()
                .title("test head title")
                .message("test head message")
                .childPosts(List.of(TmPostDto.builder()
                        .title("test child title")
                        .message("test child message")
                        .build()))
                .build();

        //WHEN
        final var tmPostActual = mapper.toEntity(tmPostDto);

        //THEN
        assertEquals(tmPostDto.getTitle(), tmPostActual.getTitle());
        assertEquals(tmPostDto.getMessage(), tmPostActual.getMessage());
        assertEquals(tmPostDto.getChildPosts().size(), tmPostActual.getChildPosts().size());
        assertEquals(tmPostDto.getChildPosts().get(0).getTitle(), tmPostActual.getChildPosts().get(0).getTitle());
        assertEquals(tmPostDto.getChildPosts().get(0).getMessage(), tmPostActual.getChildPosts().get(0).getMessage());
    }

    @Test
    void toDto() {
        //GIVEN
        final TmPostEntity tmPostEntity = TmPostEntity.builder()
                .title("test head title")
                .message("test head message")
                .childPosts(List.of(TmPostEntity.builder()
                        .title("test child title")
                        .message("test child message")
                        .build()))
                .build();
        //WHEN
        final var tmPostEntityActual = mapper.toDto(tmPostEntity);

        //THEN
        assertEquals(tmPostEntity.getTitle(), tmPostEntityActual.getTitle());
        assertEquals(tmPostEntity.getMessage(), tmPostEntityActual.getMessage());
        assertEquals(tmPostEntity.getChildPosts().size(), tmPostEntityActual.getChildPosts().size());
        assertEquals(tmPostEntity.getChildPosts().get(0).getTitle(), tmPostEntityActual.getChildPosts().get(0).getTitle());
        assertEquals(tmPostEntity.getChildPosts().get(0).getMessage(), tmPostEntityActual.getChildPosts().get(0).getMessage());
    }

}
