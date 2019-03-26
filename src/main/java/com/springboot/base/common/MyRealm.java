package com.springboot.base.common;

import com.springboot.base.result.ResultObject;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;


/**
 * Author: Damon.CF
 * Company: UBIOT.CN
 * Date: 2018/12/29$
 * Description:
 */
@Service
public class MyRealm extends AuthorizingRealm {

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.addRole("admin");

//        authorizationInfo.addStringPermission("factory:activity:query:query");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        String token = (String) authToken.getCredentials();
        ResultObject ro = UserPermissionHelp.userAuthentication(token);
        if (!ro.isSuccess()) throw new AuthenticationException("token invalid");
        return new SimpleAuthenticationInfo(token, token, MyRealm.class.getSimpleName());
    }
}
