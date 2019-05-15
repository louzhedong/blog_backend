package com.michael.blog.service;

import com.michael.blog.entity.Blog;
import com.michael.blog.enumtype.ResultMsg;
import com.michael.blog.enumtype.ReturnStatus;
import com.michael.blog.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    public List<Blog> getAllBlogList(Long pageNo, Long pageSize) {
        PageRequest pageRequest = new PageRequest(pageNo.intValue(), pageSize.intValue(), Sort.Direction.DESC, "gmt_create");
        Page<Blog> blogList = blogRepository.findAll(pageRequest);
        return (List<Blog>) blogList;
    }

    public List<Blog> getBlogListByUid(Long uid, Long pageNo, Long pageSize) {

        PageRequest pageRequest = new PageRequest(pageNo.intValue(), pageSize.intValue(), Sort.Direction.DESC, "gmt_create");
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

        blogRepository.delete(blogId.intValue());
        resultMsg.setResultCode(ReturnStatus.OK.getCode());
        resultMsg.setResultMsg(ReturnStatus.OK.getDesc());

        return resultMsg;
    }

    // 判断blog是否存在
    private boolean checkBlogIsExist(Long blogId) {
        return blogRepository.exists(blogId.intValue());
    }

    // 浏览文章，给文章增加阅读量
    public void increaseScanCount(Long blogId) {
        Blog blog = blogRepository.findOne(blogId.intValue());
        if (null != blog) {
            Integer scanCount = blog.getScanCount();
            scanCount++;
            blogRepository.updateBlogScanCount(blogId, scanCount);
        }
    }
}
