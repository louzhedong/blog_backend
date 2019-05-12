package com.michael.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Michael on 2019/5/10.
 **/
@Table(name = "auth")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String token;

    @Column(name = "gmt_create")
    private Date gmtCreate;
}
