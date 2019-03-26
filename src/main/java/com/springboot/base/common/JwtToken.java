package com.springboot.base.common;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Author: Damon.CF
 * Company: UBIOT.CN
 * Date: 2018/12/29$
 * Description:
 */
public class JwtToken implements AuthenticationToken{

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
