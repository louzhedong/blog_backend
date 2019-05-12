package com.michael.blog.repository;

import com.michael.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Michael on 2019/5/10.
 **/

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByUsername(String username);

    @Modifying
    @Query("update User set password = ?2, gmtModify = ?3 where username = ?1")
    void updateUserByUsername(
            @Param(value = "username") String username,
            @Param(value = "password") String password,
            @Param(value = "gmtModify") Date gmtModify
    );
}
