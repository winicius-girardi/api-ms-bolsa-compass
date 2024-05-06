package com.compassuol.sp.challenge.msuser.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {

    private com.compassuol.sp.challenge.msuser.entity.User user;
    public JwtUserDetails(com.compassuol.sp.challenge.msuser.entity.User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }

    public Long getId(){
        return this.user.getId();
    }




}
