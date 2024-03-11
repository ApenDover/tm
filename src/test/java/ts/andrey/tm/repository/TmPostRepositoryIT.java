package ts.andrey.tm.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ts.andrey.tm.AbstractIT;
import ts.andrey.tm.entity.TmPostEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TmRepositoryIT extends AbstractIT {

    @Autowired
    TmPostRepository tmPostRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Test
    void findAllByOwnerTest() {
        final var head = TmPostEntity.builder()
                .id(1)
                .title("папка")
                .build();
        final var expected = List.of(head);

        //WHEN
        final var actual = tmPostRepository.findAllByOwnerId(1);

        //THEN
        assertEquals(expected.get(0).getTitle(), actual.get(0).getTitle());
        assertEquals(expected.get(0).getMessage(), actual.get(0).getMessage());
    }

}
