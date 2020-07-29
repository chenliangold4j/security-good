package com.phantom5702.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/getToken")
@RestController
public class WebUsernamePasswordController {

    @Autowired
    TokenEndpoint tokenEndPoint;

    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 功能等同于 /oauth/token 获取的token
     *
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @PostMapping
    public ResponseEntity<OAuth2AccessToken> getToken
    (@RequestParam("client_id") String clientId,@RequestParam("client_secret") String client_secret,
     @RequestParam("grant_type")String grant_type,@RequestParam("username") String username, @RequestParam("password")String password) throws HttpRequestMethodNotSupportedException {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("client_id", clientId);
        parameters.put("client_secret", client_secret);
        parameters.put("grant_type", grant_type);
        parameters.put("username", username);
        parameters.put("password", password);
        // 直接调用 /oauth/token 映射的方法，不在通过url调用获取token
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = checkClient(clientId, client_secret);
        ResponseEntity<OAuth2AccessToken> result = tokenEndPoint.postAccessToken(usernamePasswordAuthenticationToken, parameters);
        return result;
    }

    public UsernamePasswordAuthenticationToken checkClient(String clientId, String clientSecret) {
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(clientId,
//                clientSecret);
        ClientDetails clientDetails = this.clientDetailsService.loadClientByClientId(clientId);
        boolean matches = this.passwordEncoder.matches(clientSecret, clientDetails.getClientSecret());
        if (!matches) {
            return null;
        } else {
            return new UsernamePasswordAuthenticationToken(clientId, clientDetails, null);
        }
    }


}
