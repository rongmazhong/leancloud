package com.smepublish.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smepublish.demo.exception.BusinessException;
import com.smepublish.demo.exception.ResultEnum;
import com.smepublish.demo.mapper.UserMapper;
import com.smepublish.demo.pojo.User;
import com.smepublish.demo.service.IUserService;
import com.smepublish.demo.utils.IdUtils;
import com.smepublish.demo.utils.MD5EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 〈〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/28
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_MAX_PAGE_SIZE = 100;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        User user = userMapper.findUserByUsername(username);
        return user;
    }

    @Override
    public PageInfo listUser(int pageNum, int pageSize) {
        if(pageSize <= 0) {
            LOGGER.warn("pageSize = {}, 不合理，赋值成默认值{}", pageSize, DEFAULT_PAGE_SIZE);
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (pageSize > DEFAULT_MAX_PAGE_SIZE) {
            LOGGER.warn("pageSize = {}, 值太大，赋值成最大默认值{}", pageSize, DEFAULT_MAX_PAGE_SIZE);
            pageSize = DEFAULT_MAX_PAGE_SIZE;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.listUser();
        PageInfo pageInfo = new PageInfo(users);
        return pageInfo;
    }

    @Override
    public User addUser(User user) {
        if (!StringUtils.isEmpty(user)){
            User old = userMapper.findUserByUsername(user.getUsername());
            if (StringUtils.isEmpty(old)) {
                long id = IdUtils.getId();
                user.setId(id);
                user.setState((byte) 1);
                String salt = MD5EncryptionUtil.getSalt(user.getUsername());
                user.setSalt(salt);
                String pwd = MD5EncryptionUtil.encryptionPwd(user.getPassword(), salt, user.getUsername(),
                        "md5", 2);
                user.setPassword(pwd);
                userMapper.insert(user);
            }else {
                throw new BusinessException(ResultEnum.USER_EXIT_ERROR);
            }
        }
        return user;
    }

    @Override
    public int deleteByPrimaryKey(long id) {
        int i = userMapper.deleteByPrimaryKey(id);
        return i;
    }
}
