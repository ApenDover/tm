package ts.andrey.tm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ts.andrey.tm.confirguration.security.UserInfoUserDetails;
import ts.andrey.tm.entity.UserInfoEntity;
import ts.andrey.tm.repository.UserInfoRepository;
import ts.andrey.tm.service.UserService;

@Component
@RequiredArgsConstructor
public class UserInfoService implements UserService {

    private final UserInfoRepository userInfoRepository;

    public UserInfoEntity getUserByName(String name) {
        return userInfoRepository.findUserInfoByName(name).orElseThrow(() -> new UsernameNotFoundException("user name not found"));
    }

    /**
     * Распаковывает Authentication в UserInfoEntity
     * @param authentication
     * @return
     */
    public UserInfoEntity unpackUserInfo(Authentication authentication) {
        final var userInfoUserDetails = (UserInfoUserDetails) authentication.getPrincipal();
        return userInfoUserDetails.getUserInfoEntity();
    }

}
