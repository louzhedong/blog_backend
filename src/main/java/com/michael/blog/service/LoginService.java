package com.michael.blog.service;

import com.michael.blog.entity.Auth;
import com.michael.blog.entity.User;
import com.michael.blog.enumtype.ResultMsg;
import com.michael.blog.enumtype.ReturnStatus;
import com.michael.blog.repository.AuthRepository;
import com.michael.blog.repository.UserRepository;
import com.michael.blog.utils.TokenProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Michael on 2019/5/10.
 * 登录Service
 **/

@Service
@Transactional
public class LoginService {

    @Autowired
    AuthRepository authRepository;
    @Autowired
    UserRepository userRepository;

    @Resource
    private UserService userService;

    public ResultMsg login(String username, String password) {
        ResultMsg resultMsg = new ResultMsg();
        if (!userService.checkIsExist(username)) {
            resultMsg.setResultCode(ReturnStatus.NOT_EXIST.getCode());
            resultMsg.setResultMsg(ReturnStatus.NOT_EXIST.getDesc());
            return resultMsg;
        }

        User user = userRepository.getByUsername(username);
        // 用户名密码正确
        if (user.getPassword().equals(password)) {
            String token = TokenProcessor.getInstance().makeToken();
            Long userId = user.getId();

            Auth existAuth = authRepository.getByUserId(userId);
            if (null == existAuth) {
                Auth auth = Auth.builder().userId(userId).token(token).gmtCreate(new Date()).build();
                authRepository.save(auth);
            } else {
                authRepository.updateOne(userId, token, new Date());
            }
            resultMsg.setData(token);
            resultMsg.setResultCode(ReturnStatus.OK.getCode());
            resultMsg.setResultMsg(ReturnStatus.OK.getDesc());
        } else {
            resultMsg.setResultCode(ReturnStatus.AUTH_ERROR.getCode());
            resultMsg.setResultMsg(ReturnStatus.AUTH_ERROR.getDesc());
        }

        return resultMsg;
    }
}
