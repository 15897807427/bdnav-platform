package com.bdxh.wallet.fallback;

import com.bdxh.common.utils.wrapper.Wrapper;
import com.bdxh.demo.entity.SysUser;
import com.bdxh.wallet.feign.SysUserControllerApi;

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
