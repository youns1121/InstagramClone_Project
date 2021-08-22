package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.User;
import com.cos.photogramstart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //패스워드는 알아서 채킹을 하니깐 신경쓸 필요가 없음
    //리턴이 잘 되면 자동으로 세션을 만든다
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null){
            return null;
        }else{
            return new PrincipalDetails(user);
        }
    }
}
