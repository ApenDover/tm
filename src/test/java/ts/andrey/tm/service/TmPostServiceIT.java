package ts.andrey.tm.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;
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
class TmPostServiceIT {

    TmPostService tmPostService;

    @Mock
    TmPostRepository tmPostRepository;

    TmPostMapper tmPostMapper = Mappers.getMapper(TmPostMapper.class);
    static final String MESSAGE = "Message";
    static final String TITLE = "Title";

    @BeforeEach
    void setup() {
        AtomicInteger id = new AtomicInteger(0); // Используем AtomicInteger для обновления id

        when(tmPostRepository.save(any()))
                .thenAnswer(invocation -> TmPostEntity.builder()
                        .id(id.incrementAndGet()) // Увеличиваем id и возвращаем его
                        .message("Message")
                        .title(TITLE)
                        .build());

        tmPostService = new TmPostService(tmPostRepository, tmPostMapper);
    }

    @Test
    void saveHeadPost() {
        //GIVEN
        final var user = UserInfoEntity.builder()
                .id(5)
                .build();
        final var tmPost = TmPostDto.builder()
                .message(MESSAGE)
                .title(TITLE)
                .childPosts(List.of(TmPostDto.builder()
                        .message(MESSAGE)
                        .title(TITLE)
                        .build(), TmPostDto.builder()
                        .message(MESSAGE)
                        .title(TITLE)
                        .childPosts(List.of(TmPostDto.builder()
                                .message(MESSAGE)
                                .title(TITLE)
                                .build()))
                        .build()))
                .build();

        //WHEN
        final var actual = tmPostService.saveHeadPost(tmPost, user);

        //THEN
        assertEquals(TITLE, actual.getTitle());
        assertEquals(MESSAGE, actual.getMessage());
        assertTrue(getTitleChild(actual));
        assertTrue(getMessageChild(actual));

    }

    private boolean getTitleChild(TmPostDto tmPostDto) {
        if (CollectionUtils.isEmpty(tmPostDto.getChildPosts())) {
            return true;
        }
        return CollectionUtils.isEmpty(tmPostDto.getChildPosts().stream()
                .filter(childPost -> !TITLE.equals(childPost.getTitle())).toList());
    }

    private boolean getMessageChild(TmPostDto tmPostDto) {
        if (CollectionUtils.isEmpty(tmPostDto.getChildPosts())) {
            return true;
        }
        return CollectionUtils.isEmpty(tmPostDto.getChildPosts().stream()
                .filter(childPost -> !MESSAGE.equals(childPost.getMessage())).toList());
    }

}
