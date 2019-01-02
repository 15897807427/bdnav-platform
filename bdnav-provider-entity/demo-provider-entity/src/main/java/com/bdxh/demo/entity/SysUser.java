package com.bdxh.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户实体类
 * @author: xuyuan
 * @create: 2018-12-27 14:42
 **/
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = -4947660920590063886L;

    private String name;

    private String passwd;
}
