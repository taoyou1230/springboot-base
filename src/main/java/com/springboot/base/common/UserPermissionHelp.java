package com.springboot.base.common;

import com.springboot.base.result.ResultObject;
import com.springboot.base.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserPermissionHelp {

    @Value("${vendingv2.auth.url}")
    private String initAuthURL;

    private static String authURL;
    @PostConstruct
    private void init() {
        authURL = initAuthURL;
    }

    /**
     * 用户认证
     * @param token
     * @return
     */
    public static ResultObject userAuthentication(String token){
        Map<String,String> map = new HashMap<String,String>();
        map.put("mToken",token);
        return HttpClientUtils.doGet("http://"+authURL+"/api_v2/token/check",null,map);
    }

    /**
     * 权限认证
     * @param token
     * @return
     */
    public ResultObject permissionAuthentication(String token,String permission){
        Map<String,String> header = new HashMap<String,String>();
        header.put("Authorization",token);
        Map<String,String> map = new HashMap<String,String>();
        map.put("permission",permission);
        return HttpClientUtils.doGet("http://"+authURL+"/api_v2/permission/checkPermission",header,map);
    }

}
