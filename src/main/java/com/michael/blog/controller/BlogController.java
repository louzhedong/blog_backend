package com.michael.blog.controller;

import com.michael.blog.entity.Blog;
import com.michael.blog.enumtype.ResultMsg;
import com.michael.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Michael on 2019/5/11.
 **/
@Slf4j
@RestController
@RequestMapping(value = "/blog")
public class BlogController {

    @Resource
    BlogService blogService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultMsg<List<Blog>> getBlogList(
            @RequestParam(value = "uid", required = true) Long uid,
            @RequestParam(value = "pageNo", required = true) Integer pageNo,
            @RequestParam(value = "pageSize", required = true) Integer pageSize

    ) {
        ResultMsg resultMsg = new ResultMsg();
        List<Blog> blogList = blogService.getBlogList(uid, pageNo, pageSize);
        resultMsg.setData(blogList);
        return resultMsg;
    }
}
