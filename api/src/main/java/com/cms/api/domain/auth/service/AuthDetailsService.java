package com.cms.api.domain.auth.service;

import com.cms.api.domain.auth.domain.AuthDetails;
import com.cms.api.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public AuthDetails loadUserByUsername(String studentNo) throws UsernameNotFoundException {
        return userRepository.findByStudentNumber(studentNo)
                .map(AuthDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
