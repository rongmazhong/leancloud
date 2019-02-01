package com.smepublish.demo.service;

import com.smepublish.demo.pojo.Permission;

import java.util.List;

/**
 * 〈〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/28
 */
public interface PermissionService {

    List<Permission> findPermissionByRoleId(long roleId);
}
