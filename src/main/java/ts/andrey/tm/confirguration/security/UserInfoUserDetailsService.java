package ts.andrey.tm.confirguration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ts.andrey.tm.data.services.UserService;

@Component
@RequiredArgsConstructor
public class UserInfoUserDetailsService implements UserDetailsService {

    private final UserInfoUserDetails userInfoUserDetails;

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userService.getUserByName(username);
        userInfoUserDetails.setUserInfo(user);
        return userInfoUserDetails;
    }

}
