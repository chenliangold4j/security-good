package com.phantom5702.oauth.impl;

import cn.hutool.json.JSONUtil;
import com.phantom5702.oauth.dao.UserDao;
import com.phantom5702.oauth.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;
    private Logger logger = LoggerFactory.getLogger(getClass());
    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("账号查询用户信息，username:{}",username);
        //将来连接数据库根据账号查询用户信息
        UserDto userDto = userDao.getUserByUsername(username);
        if(userDto == null){
            //如果用户查不到，返回null，由provider来抛出异常
            return null;
        }
        //根据用户的id查询用户的权限
        List<String> permissions = userDao.findPermissionsByUserId(userDto.getId());
        //将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        //将userDto转成json
        String principal = JSONUtil.toJsonStr(userDto);
        List<String> roles = userDao.findRolesByUserId(userDto.getId());
        String[] rolesArray =  new String[roles.size()];
        roles.toArray(rolesArray);
        UserDetails userDetails = User.withUsername(principal).password(userDto.getPassword()).roles(rolesArray).authorities(permissionArray).build();
        return userDetails;
    }
}
