package com.michael.blog.repository;

import com.michael.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Michael on 2019/5/11.
 **/
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> getByUserId(Long userId);
}
