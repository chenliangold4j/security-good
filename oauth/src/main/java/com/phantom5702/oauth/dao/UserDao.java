package com.phantom5702.oauth.dao;

import com.phantom5702.oauth.model.PermissionDto;
import com.phantom5702.oauth.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insertUser(String userName, String password, String phone) {
        String sql = "insert into t_user(username,password,fullname,mobile) value(?,?,?,?)";
        //连接数据库查询用户
        int va = jdbcTemplate.update(sql, userName, password, "测试", phone);
        return va;
    }


    //根据账号查询用户信息
    public UserDto getUserByUsername(String username) {
        String sql = "select id,username,password,fullname,mobile from t_user where username = ?";
        //连接数据库查询用户
        List<UserDto> list = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDto.class));
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    //根据用户id查询用户权限
    public List<String> findPermissionsByUserId(String userId) {

        String sql = "SELECT * FROM t_permission WHERE id IN(\n" +
                "\n" +
                "SELECT permission_id FROM t_role_permission WHERE role_id IN(\n" +
                "  SELECT role_id FROM t_user_role WHERE user_id = ? \n" +
                ")\n" +
                ")\n";

        List<PermissionDto> list = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> permissions = new ArrayList<>();
        list.forEach(c -> permissions.add(c.getCode()));
        return permissions;
    }

    public List<String> findRolesByUserId(String userId) {
        String sql = "select role_name from t_role  where id in (select role_id from t_user_role where user_id = ?)";
        List<String> list = jdbcTemplate.queryForList(sql, new Object[]{userId}, String.class);
        return list;
    }
}
