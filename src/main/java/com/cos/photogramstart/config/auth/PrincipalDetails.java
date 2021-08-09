package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter

public class PrincipalDetails implements UserDetails {

    private static final long serialVersionUid=1L;

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    //권한이 한개가 아닐 수  있음 (3개 이상의 권한)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collector = new ArrayList<>();

        collector.add(() -> {return user.getRole();});
        return collector;
        }

//        collector.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return user.getRole();
//            }
//        });


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
