package com.smepublish.demo.mapper;

import com.smepublish.demo.pojo.User;
import com.smepublish.demo.pojo.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 〈〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/28
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    User findUserByUsername(@Param("username") String username);

    /**
     * 查询用户列表<br>
     * 配合pageHelper，不要加limit后的两个参数；否则需要加上，不然数据量太大，全部查询出来可以会内存溢出
     *
     * @return
     */
    List<User> listUser();

    /**
     * 添加用户
     * @param user user
     * @return
     */
    int insert(User user);

    /**
     * deleteUser 删除用户
     * @param user user
     * @return
     */
    int deleteByExample(User user);
    /**
     * deleteByPrimaryKey 根据id删除用户
     * @param id id
     * @return int
     */
    int deleteByPrimaryKey(Long id);
    User insertSelective(User user);

    User selectByPrimaryKey(Long id);

    List<User> selectByExample(UserExample userExample);
}
