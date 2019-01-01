package com.bdxh.provider.api.demo.fallback;

import com.bdxh.common.utils.wrapper.Wrapper;
import com.bdxh.provider.api.demo.feign.SysUserControllerApi;
import com.bdxh.provider.entity.demo.SysUser;

/**
 * @description: hystrix降级服务
 * @author: xuyuan
 * @create: 2018-12-27 17:22
 **/
public class SysUserControllerFallback implements SysUserControllerApi {

    @Override
    public Wrapper<SysUser> getSysUser(String name) {
        return null;
    }
}
