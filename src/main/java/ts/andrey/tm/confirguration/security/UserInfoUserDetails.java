package ts.andrey.tm.confirguration.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ts.andrey.tm.entity.UserInfoEntity;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserInfoUserDetails implements UserDetails {

    @Getter
    @Setter
    private UserInfoEntity userInfoEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + userInfoEntity.getRole()));
    }

    @Override
    public String getPassword() {
        return userInfoEntity.getHexPassword();
    }

    @Override
    public String getUsername() {
        return userInfoEntity.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
