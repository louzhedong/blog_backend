package com.michael.blog.repository;

import com.michael.blog.entity.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Michael on 2019/5/11.
 **/
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findAllByUserId(Long userId, Pageable pageable);


    @Modifying
    @Query("update Blog set content = ?2, gmtModify = ?3 where id = ?1")
    void updateBlogById(
            @Param(value = "id") Long id,
            @Param(value = "content") String content,
            @Param(value = "gmtModify") Date gmtModify
    );
}
