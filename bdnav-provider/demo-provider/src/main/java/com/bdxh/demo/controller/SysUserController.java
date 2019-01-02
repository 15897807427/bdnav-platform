package com.bdxh.demo.controller;

import com.bdxh.common.utils.wrapper.WrapMapper;
import com.bdxh.common.utils.wrapper.Wrapper;
import com.bdxh.demo.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 用户控制器
 * @author: xuyuan
 * @create: 2018-12-27 14:48
 **/
@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    @RequestMapping("/getByName")
    @ResponseBody
    public Wrapper<SysUser> getSysUser(String name){
        SysUser sysUser=new SysUser();
        sysUser.setName(name);
        sysUser.setPasswd("123456");
        return WrapMapper.ok(sysUser);
    }
}
