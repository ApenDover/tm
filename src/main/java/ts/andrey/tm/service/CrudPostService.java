package ts.andrey.tm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ts.andrey.tm.confirguration.security.UserInfoUserDetails;
import ts.andrey.tm.data.entity.UserInfo;
import ts.andrey.tm.data.services.TmPostService;
import ts.andrey.tm.dto.TmPostDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrudPostService {

    private final TmPostService tmPostService;

    public boolean publishPost(TmPostDto tmPostDto, Authentication authentication) {
        tmPostService.saveHeadPost(tmPostDto, unpackUserInfo(authentication));
        return true;
    }

    public List<TmPostDto> readPost(Authentication authentication) {
        unpackUserInfo(authentication);
        return tmPostService.readAllTmPostForUser(unpackUserInfo(authentication));
    }

    private UserInfo unpackUserInfo(Authentication authentication) {
        final var userInfoUserDetails = (UserInfoUserDetails) authentication.getPrincipal();
        return userInfoUserDetails.getUserInfo();
    }
}
