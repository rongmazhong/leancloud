package com.smepublish.demo.service;

import com.smepublish.demo.pojo.Role;

import java.util.List;

/**
 * 〈〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/28
 */
public interface RoleService {

    List<Role> findRoles(String username);
}
