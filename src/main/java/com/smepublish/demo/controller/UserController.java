package com.smepublish.demo.controller;

import com.github.pagehelper.PageInfo;
import com.smepublish.demo.pojo.User;
import com.smepublish.demo.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈用户管理〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/24
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping("/findUserByUsername")
    public R findUserByUsername(String username) {
        LOGGER.info("username = {}", username);
        User user = userService.findUserByUsername(username);
        return R.ok().put("user", user);
    }

    /**
     * userList 用户列表
     *
     * @param pageNum  页数
     * @param pageSize 每页数量
     * @return R
     * @author MZRong
     * @date 2019/1/29 9:46
     */
    @RequestMapping("/userList")
    @RequiresPermissions("user:view")
    public R userList(int pageNum, int pageSize) {
        PageInfo pageInfo = userService.listUser(pageNum, pageSize);
        LOGGER.info("pageNum = {}, pageSize = {},pageInfo ={}", pageNum, pageSize, pageInfo);
        return R.ok().put("page", pageInfo);
    }

    /**
     * 用户添加;
     *
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("user:add")
    public R userAdd(@RequestBody User user) {
        User addUser = userService.addUser(user);
        return R.ok().put("data", addUser);
    }

    /**
     * 用户删除;
     *
     * @return
     */
    @GetMapping("/userDel/{id}")
    @RequiresPermissions("user:del")
    public R userDel(@PathVariable long id) {
        userService.deleteByPrimaryKey(id);
        return R.ok().put("msg", "删除成功");
    }
}
