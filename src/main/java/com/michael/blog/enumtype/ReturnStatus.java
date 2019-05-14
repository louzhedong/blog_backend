package com.michael.blog.enumtype;

/**
 * Created by Michael on 2019/5/9.
 **/
public enum ReturnStatus {
    UNKNOWN_CODE(-1, ""),
    OK(0, "成功"),
    IS_EXIST(1, "账户已存在"),
    NOT_EXIST(2, "用户不存在"),
    AUTH_ERROR(3, "用户名或密码错误"),
    AUTH_LIMIT(4, "权限不足"),
    BLOG_NOT_EXIST(5, "博客不存在"),;

    ReturnStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final int code;
    private final String desc;

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return desc;
    }

    public static ReturnStatus fromCode(int code) {
        for (ReturnStatus v : ReturnStatus.values()) {
            if (v.code == code) {
                return v;
            }
        }
        return UNKNOWN_CODE;
    }

    public static ReturnStatus getEnum(String desc) {
        for (ReturnStatus v : ReturnStatus.values()) {
            if (v.getDesc().equalsIgnoreCase(desc)) {
                return v;
            }
        }
        return UNKNOWN_CODE;
    }
}
