package com.michael.blog.service;

import com.michael.blog.entity.User;
import com.michael.blog.enumtype.ResultMsg;
import com.michael.blog.enumtype.ReturnStatus;
import com.michael.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Michael on 2019/5/9.
 **/
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    // 新建用户
    public void addUser(String username, String password) {
        User user = User.builder().username(username).password(password).gmtCreate(new Date()).gmtModify(new Date()).build();
        userRepository.save(user);
    }

    // 判断用户是否已存在
    public boolean checkIsExist(String username) {
        User user = userRepository.getByUsername(username);
        if (null == user) {
            return false;
        } else {
            return true;
        }
    }

    // 修改密码
    public ResultMsg editUser(String username, String password, String newPassword) {
        ResultMsg resultMsg = new ResultMsg();

        if (!this.checkIsExist(username)) {
            resultMsg.setResultCode(ReturnStatus.NOT_EXIST.getCode());
            resultMsg.setResultMsg(ReturnStatus.NOT_EXIST.getDesc());
            return resultMsg;
        }

        User user = userRepository.getByUsername(username);
        if (!user.getPassword().equals(password)) {
            resultMsg.setResultCode(ReturnStatus.AUTH_ERROR.getCode());
            resultMsg.setResultMsg(ReturnStatus.AUTH_ERROR.getDesc());
            return resultMsg;
        }

        userRepository.updateUserByUsername(username, newPassword, new Date());
        resultMsg.setResultCode(ReturnStatus.OK.getCode());
        resultMsg.setResultMsg(ReturnStatus.OK.getDesc());

        return resultMsg;
    }
}
