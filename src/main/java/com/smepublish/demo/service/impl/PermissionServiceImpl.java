package com.smepublish.demo.service.impl;

import com.smepublish.demo.mapper.PermissionMapper;
import com.smepublish.demo.pojo.Permission;
import com.smepublish.demo.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/28
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> findPermissionByRoleId(long roleId) {
        return permissionMapper.findPermissionByRoleId(roleId);
    }
}
