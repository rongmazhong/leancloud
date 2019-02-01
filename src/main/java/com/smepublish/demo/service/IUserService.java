package com.smepublish.demo.service;

import com.github.pagehelper.PageInfo;
import com.smepublish.demo.pojo.User;

/**
 * 〈〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/28
 */
public interface IUserService {
    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     *  分页查询用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo listUser(int pageNum, int pageSize);

    /**
     * addUser 添加用户
     * @param user 用户详细
     * @return User
     */
    User addUser(User user);

    /**
     * deleteByPrimaryKey 根据id删除用户
     * @param id id
     * @return int
     * @author MZRong
     * @date 2019/1/29 15:49
     */
    int deleteByPrimaryKey(long id);
}
