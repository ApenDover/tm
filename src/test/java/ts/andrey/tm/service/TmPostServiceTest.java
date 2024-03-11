package ts.andrey.tm.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ts.andrey.tm.dto.TmPostDto;
import ts.andrey.tm.entity.TmPostEntity;
import ts.andrey.tm.entity.UserInfoEntity;
import ts.andrey.tm.mapper.TmPostMapper;
import ts.andrey.tm.repository.TmPostRepository;
import ts.andrey.tm.service.impl.TmPostService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TmPostServiceTest {

    static final String MESSAGE = "Message";

    static final String TITLE = "Title";

    @Mock
    TmPostRepository tmPostRepository;

    TmPostMapper mapper = Mappers.getMapper(TmPostMapper.class);

    TmPostService tmPostService;

    @BeforeEach
    void setUp() {
        tmPostService = new TmPostService(tmPostRepository, mapper);
    }

    @Test
    void saveHeadPost() {
        //GIVEN

        final var id = new AtomicInteger(0);
        when(tmPostRepository.save(any()))
                .thenAnswer(invocation -> TmPostEntity.builder()
                        .id(id.incrementAndGet())
                        .message(MESSAGE)
                        .title(TITLE)
                        .build());

        final var owner = UserInfoEntity.builder()
                .id(1)
                .role("ADMIN")
                .name("Name")
                .hexPassword("hex")
                .build();

        final var tmPostDto = TmPostDto.builder()
                .message(MESSAGE)
                .title(TITLE)
                .build();


        //WHEN
        final var actual = tmPostService.saveHeadPost(tmPostDto, owner);

        //THEN
        assertEquals(actual.getMessage(), MESSAGE);
        assertEquals(actual.getTitle(), TITLE);
        assertEquals(actual.getId(), 1);
    }

    @Test
    void readAllTmPostForUser() {
        //GIVEN
        final var ownerId = 1;
        when(tmPostRepository.findAllByOwnerId(ownerId)).thenReturn(List.of(TmPostEntity.builder()
                .message(MESSAGE)
                .title(TITLE)
                .childPosts(List.of(TmPostEntity.builder()
                        .message(MESSAGE)
                        .title(TITLE)
                        .build(), TmPostEntity.builder()
                        .message(MESSAGE)
                        .title(TITLE)
                        .build()))
                .build()));

        final var owner = UserInfoEntity.builder()
                .id(ownerId)
                .role("ADMIN")
                .name("Name")
                .hexPassword("hex")
                .build();

        //WHEN
        final var actual = tmPostService.readAllTmPostForUser(owner);

        //THEN
        assertEquals(MESSAGE, actual.get(0).getMessage());
        assertEquals(TITLE, actual.get(0).getTitle());
        assertEquals(MESSAGE, actual.get(0).getChildPosts().get(0).getMessage());
        assertEquals(TITLE, actual.get(0).getChildPosts().get(0).getTitle());
        assertEquals(MESSAGE, actual.get(0).getChildPosts().get(1).getMessage());
        assertEquals(TITLE, actual.get(0).getChildPosts().get(1).getTitle());

    }


}