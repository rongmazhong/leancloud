package com.smepublish.demo.service.impl;

import com.smepublish.demo.mapper.RoleMapper;
import com.smepublish.demo.pojo.Role;
import com.smepublish.demo.service.RoleService;
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
public class RoleServiceImpl implements RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findRoles(String username) {
        return roleMapper.findRoleByUsername(username);
    }
}
