package com.michael.blog.controller;

import com.michael.blog.enumtype.ResultMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Michael on 2019/5/11.
 **/
@Slf4j
@RestController
@RequestMapping(value = "/blog")
public class BlogController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultMsg getBlogList(
            @RequestParam(value = "token", required = true) String token
    ) {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setData("success");
        return resultMsg;
    }
}
