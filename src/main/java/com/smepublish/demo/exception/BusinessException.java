package com.smepublish.demo.exception;

/**
 * 〈〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/30
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -436453184280436799L;

    /**
     * 自定义错误码
     */
    private Integer code;

    public BusinessException() {
    }

    /**
     * 自定义构造器,必须输入错误码及内容
     */
    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
