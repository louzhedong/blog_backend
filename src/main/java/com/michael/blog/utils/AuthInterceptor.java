package com.michael.blog.utils;

import com.alibaba.fastjson.JSON;
import com.michael.blog.entity.Auth;
import com.michael.blog.enumtype.ResultMsg;
import com.michael.blog.enumtype.ReturnStatus;
import com.michael.blog.repository.AuthRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Michael on 2019/5/11.
 * 验证登录权限
 **/
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    AuthRepository authRepository;

    private Long start;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        start = System.currentTimeMillis();
        String token = request.getParameter("token");
        String uid = request.getParameter("uid");
        if (StringUtils.isEmpty(token)) {
            this.getTokenFailReturnResult(response);
            return false;
        }
        Auth auth = authRepository.getByToken(token);
        if ((null == auth) || !auth.getId().toString().equals(uid)) {
            this.getTokenFailReturnResult(response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("Interceptor cost=" + (System.currentTimeMillis() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    private void getTokenFailReturnResult(HttpServletResponse httpServletResponse) throws IOException {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setResultCode(ReturnStatus.AUTH_LIMIT.getCode());
        resultMsg.setResultMsg(ReturnStatus.AUTH_LIMIT.getDesc());
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        httpServletResponse.getWriter().print(JSON.toJSONString(resultMsg));
    }
}
