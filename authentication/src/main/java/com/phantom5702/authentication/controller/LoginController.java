package com.phantom5702.authentication.controller;

import com.phantom5702.common.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class LoginController {


    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    /**
     * 身份认证
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Result requireAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(httpServletRequest,httpServletResponse);
        if(savedRequest != null){
            String target = savedRequest.getRedirectUrl();
            System.out.println("引发跳转的请求是："+target);
            if(target.endsWith(".html")){
                //这里可以考虑跳转到登录页
                redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"http://localhost:8001/api/sms/test.html");
            }
        }
        return Result.createDefaultErrorMessage("访问的服务需要身份认证，请引导用户到登录页面");
    }

    @RequestMapping("/authentication/mobile")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Result mobileAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(httpServletRequest,httpServletResponse);
        return Result.createDefaultErrorMessage("访问的服务需要身份认证，请引导用户到登录页面");
    }
    /**
     * 测试资源1
     * @return
     */
    @PostMapping(value = "/user/{id}")
    public String userId1(@PathVariable("id") String id){
        return id+" 访问资源1";
    }


    @GetMapping(value = "/user/{id}")
    public String userId2(@PathVariable("id") String id){
        return id+" 访问资源1";
    }


    /**
     * 测试资源1
     * @return
     */
    @GetMapping(value = "/r/r1")
    public String r1(){
        return getUsername()+" 访问资源1";
    }

    //获取当前用户信息
    private String getUsername(){
        String username = null;
        //当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //用户身份
        Object principal = authentication.getPrincipal();
        if(principal == null){
            username = "匿名";
        }
        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        }else{
            username = principal.toString();
        }
        return username;
    }
}
