package com.smepublish.demo.exception;

/**
 * 〈全局结果枚举类〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/30
 */

public enum ResultEnum {
    /**
     * 未知错误
     */
    UNKONW_ERROR(-1, "未知错误"),
    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    /**
     * 失败
     */
    ERROR(1, "失败"),
    /**
     * 添加用户，用户名已存在
     */
    USER_EXIT_ERROR(1000000001, "用户名已存在"),

    /**
     * 用户权限不足
     */
    NO_PERMISSION_ERROR(1000000002, "权限不足"),

    /**
     * 登录失败
     */
    LOGIN_FAIL(1000000003, "登录失败，账号或密码错误!"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
