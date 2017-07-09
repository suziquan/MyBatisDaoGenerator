package com.example.demo.model;

import lombok.Data;
import java.io.Serializable;

import java.util.Date;

@Data
public class User implements Serializable {

    /**
    *  主键
    */
    private Long id;

    /**
    *  姓名
    */
    private String name;

    /**
    *  年龄
    */
    private Integer age;

    /**
    *  手机号
    */
    private String mobile;

    /**
    *  邮箱
    */
    private String email;

    /**
    *  创建时间
    */
    private Date createdAt;

    /**
    *  更新时间
    */
    private Date updatedAt;

}

