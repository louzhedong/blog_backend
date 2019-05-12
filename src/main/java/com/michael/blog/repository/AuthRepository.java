package com.michael.blog.repository;

import com.michael.blog.entity.Auth;
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
public interface AuthRepository extends JpaRepository<Auth, Integer> {
    Auth getByUserId(Long userId);

    Auth getByToken(String token);

    @Modifying
    @Query("update Auth set token = ?2, gmtCreate = ?3 where userId = ?1")
    void updateOne(
            @Param(value = "userId") Long userId,
            @Param(value = "token") String token,
            @Param(value = "gmtCreate") Date gmtCreate
    );
}
