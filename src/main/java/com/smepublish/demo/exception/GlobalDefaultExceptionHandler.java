package com.smepublish.demo.exception;

import com.smepublish.demo.controller.R;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * 〈自定义全局异常拦截〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/30
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    /**
     * defaultExceptionHandler 声明要捕获的异常
     *
     * @param request 请求
     * @param e       exception
     * @return com.smepublish.demo.controller.R
     * @author MZRong
     * @date 2019/1/30 10:39
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R defaultExceptionHandler(HttpServletRequest request, Exception e) {
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        if (e instanceof BusinessException) {
            LOGGER.error(this.getClass() + ",业务异常：" + e.getMessage() + ", url:" + url + ", method:" + method);
            BusinessException businessException = (BusinessException) e;
            return R.error(businessException.getCode(), businessException.getMessage());
        } else if (e instanceof UnauthorizedException) {
            return R.error(ResultEnum.NO_PERMISSION_ERROR.getCode(), ResultEnum.NO_PERMISSION_ERROR.getMsg());
        }
        //未知错误
        return R.error(-1, "系统异常：" + e);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public String noPermissionError(HttpServletRequest request, Exception e) {
        return "error/403";
    }
}
