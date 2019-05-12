package com.michael.blog.controller;

import com.michael.blog.enumtype.ResultMsg;
import com.michael.blog.enumtype.ReturnStatus;
import com.michael.blog.service.LoginService;
import com.michael.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Michael on 2019/5/9.
 **/
@Slf4j
@RestController
@RequestMapping(value = "/auth")
public class UserController {

    @Resource
    UserService userService;
    @Resource
    LoginService loginService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ResultMsg addUser(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ) {
        ResultMsg resultMsg = new ResultMsg();
        if (userService.checkIsExist(username)) {
            resultMsg.setResultCode(ReturnStatus.IS_EXIST.getCode());
            resultMsg.setResultMsg(ReturnStatus.IS_EXIST.getDesc());
            return resultMsg;
        }
        userService.addUser(username, password);
        resultMsg.setResultCode(ReturnStatus.OK.getCode());
        resultMsg.setResultMsg(ReturnStatus.OK.getDesc());
        return resultMsg;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResultMsg editUser(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "newPassword", required = true) String newPassword
    ) {
        return userService.editUser(username, password, newPassword);
    }

    // 登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultMsg login(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ) {
        return loginService.login(username, password);
    }
}
