package com.compassuol.sp.challenge.msuser.jwt;

import com.compassuol.sp.challenge.msuser.entity.User;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new JwtUserDetails(user);
    }
    public JwtToken getTokenAutheticated(String email){
        User user = userRepository.findByEmail(email);
        return  JwtUtils.createToken(user.getEmail());
    }
}
