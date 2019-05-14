package com.michael.blog.service;

import com.michael.blog.entity.Blog;
import com.michael.blog.enumtype.ResultMsg;
import com.michael.blog.enumtype.ReturnStatus;
import com.michael.blog.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Michael on 2019/5/14.
 **/
@Slf4j
@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    public List<Blog> getBlogList(Long uid, int pageNo, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "gmt_create");
        List<Blog> blogList = blogRepository.findAllByUserId(uid, pageRequest);
        return blogList;
    }

    // 添加blog
    public void addBlog(Long userId, String content) {
        Blog blog = Blog.builder().content(content).gmtCreate(new Date()).gmtModify(new Date()).scanCount(0).build();
        blogRepository.save(blog);
    }

    // 修改blog
    public ResultMsg editBlog(Long blogId, String content) {
        ResultMsg resultMsg = new ResultMsg();

        if (!this.checkBlogIsExist(blogId)) {
            resultMsg.setResultCode(ReturnStatus.BLOG_NOT_EXIST.getCode());
            resultMsg.setResultMsg(ReturnStatus.BLOG_NOT_EXIST.getDesc());
            return resultMsg;
        }

        blogRepository.updateBlogById(blogId, content, new Date());

        resultMsg.setResultCode(ReturnStatus.OK.getCode());
        resultMsg.setResultMsg(ReturnStatus.OK.getDesc());

        return resultMsg;
    }


    // 删除blog

    public ResultMsg delBlog(Long blogId) {
        ResultMsg resultMsg = new ResultMsg();

        if (!this.checkBlogIsExist(blogId)) {
            resultMsg.setResultCode(ReturnStatus.BLOG_NOT_EXIST.getCode());
            resultMsg.setResultMsg(ReturnStatus.BLOG_NOT_EXIST.getDesc());
            return resultMsg;
        }

        blogRepository.deleteById(blogId.intValue());
        resultMsg.setResultCode(ReturnStatus.OK.getCode());
        resultMsg.setResultMsg(ReturnStatus.OK.getDesc());

        return resultMsg;
    }

    // 判断blog是否存在
    private boolean checkBlogIsExist(Long blogId) {
        Blog blog = blogRepository.getOne(blogId.intValue());
        if (null == blog) {
            return false;
        } else {
            return true;
        }
    }
}
