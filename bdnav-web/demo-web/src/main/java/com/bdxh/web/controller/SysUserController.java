package com.bdxh.web.controller;

import com.bdxh.common.utils.wrapper.WrapMapper;
import com.bdxh.common.utils.wrapper.Wrapper;
import com.bdxh.provider.entity.demo.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: web服务
 * @author: xuyuan
 * @create: 2018-12-27 17:58
 **/
@RequestMapping("/sysUser")
@Controller
public class SysUserController {
    @RequestMapping("/getByName")
    @ResponseBody
    public Wrapper<SysUser> getSysUser(String name){
        return WrapMapper.ok();
    }

}
