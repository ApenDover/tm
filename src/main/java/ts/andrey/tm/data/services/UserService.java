package ts.andrey.tm.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ts.andrey.tm.data.entity.UserInfo;
import ts.andrey.tm.data.repository.UserInfoRepository;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserInfoRepository userInfoRepository;

    public UserInfo getUserByName(String name) {
        return userInfoRepository.findUserInfoByName(name).orElseThrow(() -> new UsernameNotFoundException("user name not found"));
    }

}
