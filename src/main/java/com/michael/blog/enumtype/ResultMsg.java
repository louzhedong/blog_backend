package com.michael.blog.enumtype;

import lombok.Data;

/**
 * Created by Michael on 2019/5/9.
 **/

@Data
public class ResultMsg<T> {
    private int resultCode;
    private String resultMsg;
    private T data;
    private int totalCount;

}
