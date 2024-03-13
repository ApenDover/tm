package ts.andrey.tm.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ts.andrey.tm.AbstractIT;
import ts.andrey.tm.entity.UserInfoEntity;
import ts.andrey.tm.service.impl.UserInfoService;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoServiceIT extends AbstractIT {

    @Autowired
    UserInfoService userInfoService;

    @Test
    void getUserByName() {
        final var userName = "Andrey";
        final var expected = UserInfoEntity.builder()
                .id(1)
                .name(userName)
                .hexPassword("{SHA-256}{test}35bfe5fe27bd8fa134bfb2399e28620758076b2e91e6f6275fd87f767e0991a8")
                .role("ADMIN")
                .build();


        //WHEN
        final var actual = userInfoService.getUserByName(userName);

        //THEN
        assertEquals(expected, actual);

    }

}
